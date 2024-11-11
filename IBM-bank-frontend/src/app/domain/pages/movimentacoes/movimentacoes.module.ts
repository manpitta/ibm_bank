import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MovimentacoesRoutingModule } from './movimentacoes-routing.module';
import { FormMovimentacaoComponent } from './form-movimentacao/form-movimentacao.component';
import { ListarMovimentacoesComponent } from './listar-movimentacoes/listar-movimentacoes.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from 'src/app/material/material.module';
import { AitFormModule } from 'src/app/widget/ait-form/ait-form.module';


@NgModule({
  declarations: [
    FormMovimentacaoComponent,
    ListarMovimentacoesComponent
  ],
  imports: [
    CommonModule,
    MovimentacoesRoutingModule,
    MaterialModule,
    ReactiveFormsModule,
    AitFormModule,
  ]
})
export class MovimentacoesModule { }
