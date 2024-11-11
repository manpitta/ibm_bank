import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { TableFormFields } from 'src/app/widget/ait-form/ait-form.component';

@Component({
  standalone: false,
  selector: 'app-form-dialog',
  templateUrl: './form-dialog.component.html',
  styleUrls: ['./form-dialog.component.scss']
})
export class FormDialogComponent {
  constructor(public dialogRef: MatDialogRef<FormDialogComponent>, private fb: FormBuilder) { }

  fieldsCadastro: TableFormFields = [
    [
      { name: 'Limite', type: 'number', formControlName: 'limiteCredito', fieldFlexGrow:1},
    ],[
      { name: 'Data de Fechamento', type: 'date', formControlName: 'dataFechamento', fieldFlexGrow: 1, validators: {maskPattern: '00'}, fieldMinWidth: '100px'},
    ],
    [{ name: 'Data de Pagamento', type: 'date', formControlName: 'dataPagamento', fieldFlexGrow: 1},]
  ]

  formCadastroCredito = this.fb.group({
    limiteCredito: ['',Validators.required],
    dataFechamento: ['',Validators.required],
    dataPagamento: ['',Validators.required]
  })

  cancelar() {
    this.dialogRef.close();
  }

  confirmar() {
    this.dialogRef.close(this.formCadastroCredito.value);
  }
}
