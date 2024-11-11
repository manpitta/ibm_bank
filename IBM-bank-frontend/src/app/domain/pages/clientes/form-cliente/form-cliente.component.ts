import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { switchMap } from 'rxjs';
import { ClienteApiService } from 'src/app/core/services/api/cliente/cliente.api.service';
import { FormField, TableFormFields } from 'src/app/widget/ait-form/ait-form.component';
import { FormDialogComponent } from 'src/app/domain/pages/clientes/components/form-dialog/form-dialog.component';
import { Cliente } from 'src/shared/models/cliente';
import { ContaCreditoApiService } from 'src/app/core/services/api/contaCredito/contaCredito.api.service';

@Component({
  standalone: false,
  selector: 'app-form-cliente',
  templateUrl: './form-cliente.component.html',
  styleUrls: ['./form-cliente.component.scss'],
})
export class FormClienteComponent implements OnInit{

  constructor(
    private fb: FormBuilder, private router:Router, 
    private _snackBar: MatSnackBar, private clienteApiService: ClienteApiService,
    private route: ActivatedRoute, private contaCreditoApiService: ContaCreditoApiService
  ) { }

  readonly dialog = inject(MatDialog)

  fieldsClientePrincipal: TableFormFields = [
    [
      { name: 'Nome', type: 'text', formControlName: 'nome', fieldFlexGrow:1},
    ],[
      { name: 'Idade', type: 'text', formControlName: 'idade', fieldWidth: '20%', validators: {maskPattern: '00'}, fieldMinWidth: '100px'},
      { name: 'Email', type: 'text', formControlName: 'email', fieldFlexGrow: 1},
    ],
  ]

  fieldsClienteConta: TableFormFields = [
    [
      { name: 'Numero da Conta', type: 'text', formControlName: 'numeroConta', fieldFlexGrow: 1,validators: {maskPattern: '0*'}},
    ],
  ]


  formCliente = this.fb.group({
    nome: ['',Validators.required],
    idade: ['',Validators.required],
    email: ['',Validators.required],
    numeroConta: ['',Validators.required]
  })

  queryParams: any;
  isEditing: boolean = false;

  ngOnInit(): void {
    this.route.url.subscribe(url => {
      if (url[0].path == 'editar') {
        this.formCliente.disable()
        this.route.queryParams.subscribe(params => {
          this.queryParams = params;
        })
        this.route.queryParams.pipe(
          switchMap((params) => {
            this.queryParams = params;
            return this.clienteApiService.getClienteById(params['id'])
          })
        ).subscribe(cliente => {
          this.formCliente.patchValue({
            nome: cliente.nome,
            idade: cliente.idade.toString(),
            email: cliente.email,
            numeroConta: cliente.numeroConta?.toString()
          })
        })
      }
    })
  }

  salvarCliente() {
    let cliente:Cliente = {
      nome: this.formCliente.get('nome')?.value??'',
      idade: parseInt(this.formCliente.get('idade')?.value??'')??0,
      email: this.formCliente.get('email')?.value??'',
      numeroConta: parseInt(this.formCliente.get('numeroConta')?.value??'')??0
    };

    let request;
    if (this.isEditing) {
      request = this.clienteApiService.updateCliente(this.queryParams.id, cliente)
    } else
      request = this.clienteApiService.createCliente(cliente, cliente.numeroConta);
    request.subscribe(() => {
      this._snackBar.open("Cliente "+ (this.isEditing?"editado":"salvo") +" com sucesso", "fechar", {duration: 5000, panelClass: ['snackbar-success'], horizontalPosition: 'end'});
      this.router.navigate(['/clientes']);
    })
  }
  editarCliente() {
    this.isEditing = true;
    this.formCliente.enable();
    this.formCliente.controls.numeroConta.disable();
  }

  cadastrarCredito() {
    this.dialog.open(FormDialogComponent).afterClosed().subscribe(result => {
      if (result) {
        console.log(result)
        this.contaCreditoApiService.createContaCredito(result, this.queryParams['id']).subscribe(() => {
          this._snackBar.open("Conta de crédito cadastrada com sucesso", "fechar", {duration: 5000, panelClass: ['snackbar-success'], horizontalPosition: 'end'});
        })
      }
    })
  }
}
