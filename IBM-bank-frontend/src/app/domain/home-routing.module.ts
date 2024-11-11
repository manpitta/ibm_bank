import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TelaComponent } from './pages/tela/tela.component';

const routes: Routes = [
  {
    path: '',
    component: TelaComponent,
    children: [
      {
        path: '',
        pathMatch: 'full',
        redirectTo: 'clientes'
      },
      {
        path: 'clientes',
        loadChildren: () => import('./pages/clientes/clientes.module').then(m => m.ClientesModule)
      },
      {
        path: 'movimentacoes',
        loadChildren: () => import('./pages/movimentacoes/movimentacoes.module').then(m => m.MovimentacoesModule)
      },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
