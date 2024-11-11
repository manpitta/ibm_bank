import { Component, OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { map, Observable, of, startWith, switchMap } from 'rxjs';
import { ContaApiService } from 'src/app/core/services/api/conta/conta.api.service';
import { ContaCreditoApiService } from 'src/app/core/services/api/contaCredito/contaCredito.api.service';
import { MovimentacaoApiService } from 'src/app/core/services/api/movimentacao/movimentacao.api.service';
import { TableFormFields } from 'src/app/widget/ait-form/ait-form.component';
import { Conta, TipoConta } from 'src/shared/models/conta';
import { ContaCredito } from 'src/shared/models/contaCredito';
import { Movimentacao, eColunaXPropMovimentacao } from 'src/shared/models/movimentacao';
import { ScreenSizeService } from 'src/shared/services/screen-size.service';

@Component({
  standalone: false,
  selector: 'app-listar-Movimentacoes',
  templateUrl: './listar-movimentacoes.component.html',
  styleUrls: ['./listar-movimentacoes.component.scss']
})
export class ListarMovimentacoesComponent implements OnInit {

  screenSize!: string;
  movimentacoes$!: Observable<Movimentacao[]>;
  conta$!: Observable<Conta>;
  contaCredito$!: Observable<ContaCredito>
  todasColunas: string[] = ['dataMovimentacao', 'descricao', 'status', 'tipo', 'valor'];
  queryParams: any;
  fieldsConta: TableFormFields = [
    [{ name: 'Tipo de Conta', type: 'buttonToggle', formControlName: 'tipoConta', 
      options:[
        ['Conta Corrente', 'corrente'],
        ['Crédito', 'credito']
      ]}]
  ]
  formContas: FormGroup = this.fb.group({
    tipoConta: ['corrente', Validators.required],
  });
  
  constructor(private screenSizeService: ScreenSizeService, private router: Router,
    private route: ActivatedRoute, private movimentacoesApiService: MovimentacaoApiService,
    private contaApiService: ContaApiService, public contaCreditoApiService: ContaCreditoApiService,
    private _snackBar: MatSnackBar, private fb: FormBuilder) { }

  ngOnInit(): void {   
    this.screenSizeService.screenSize().subscribe(size => {
      this.screenSize = size;
    }); 

    this.route.queryParams.subscribe(params => {
      this.queryParams = params;
        if (params['contaId']) {
          this.conta$ = this.contaApiService.getContaById(params['contaId'])
          this.contaCredito$ = this.contaApiService.getContaById(params['contaId']).pipe(
            switchMap(conta => {
              return this.contaCreditoApiService.getContaCreditoById(conta.contaCreditoId?? 0)
            })
          )
          this.movimentacoes$ = this.movimentacoesApiService.listarMovimentacoesPorConta(params['contaId'])
        } else {
          this._snackBar.open("Nenhum cliente selecionado. Retornando à listagem de clientes", "fechar", {duration: 5000, panelClass: ['snackbar-success'], horizontalPosition: 'end'});
          //this.router.navigate(['/clientes'])
        }
    })

  }
}
