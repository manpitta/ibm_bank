import { ChangeDetectorRef, Component, OnInit} from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Cliente} from 'src/shared/models/cliente';
import { ScreenSizeService } from 'src/shared/services/screen-size.service';
import { ClienteApiService } from 'src/app/core/services/api/cliente/cliente.api.service';
import { Observable } from 'rxjs';

@Component({
  standalone: false,
  selector: 'app-listar-clientes',
  templateUrl: './listar-clientes.component.html',
  styleUrls: ['./listar-clientes.component.scss']
})
export class ListarClientesComponent implements OnInit {

  screenSize!: string;
  clientes$: Observable<Cliente[]> = this.clienteService.getAllClientes();
  todasColunas: string[] = ['Nome', 'Idade', 'Email', 'Acoes'];
  
  constructor(private screenSizeService: ScreenSizeService, 
              private router: Router, private clienteService: ClienteApiService, private cdr: ChangeDetectorRef) { }

  ngOnInit(): void {   
    this.screenSizeService.screenSize().subscribe(size => {
      this.screenSize = size;
    }); 
  }

  editEl(id: number) {
    this.router.navigate(['/clientes/editar'],
    {queryParams: {id: id}})
  }

  verMovimentacoes(id: number) {
    this.router.navigate(['/movimentacoes'],
    {queryParams: {contaId: id}})
  }
}
