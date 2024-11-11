import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClientesRoutingModule } from './clientes-routing.module';
import { ListarClientesComponent } from './listar-clientes/listar-clientes.component';
import { MaterialModule } from 'src/app/material/material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { FormClienteComponent } from './form-cliente/form-cliente.component';
import { MatSortModule } from '@angular/material/sort';
import { AitFormModule } from 'src/app/widget/ait-form/ait-form.module';
import { FormDialogComponent } from './components/form-dialog/form-dialog.component';


@NgModule({
  declarations: [
    ListarClientesComponent,
    FormClienteComponent,
    FormDialogComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    ClientesRoutingModule,
    ReactiveFormsModule,
    MatSortModule,
    AitFormModule
  ]
})
export class ClientesModule { }
