import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Cliente } from '../../../../../shared/models/cliente';

@Injectable({
  providedIn: 'root'
})
export class ClienteApiService {
  readonly BASE_URL = environment.IBMBankApi.baseUrl;
  readonly ENDPOINT = environment.IBMBankApi.endpoints.clientes;
  readonly FULL_URL = `${this.BASE_URL}/${this.ENDPOINT}`;

  constructor(private http: HttpClient) { }

  // Método para buscar todos os clientes
  getAllClientes(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(this.FULL_URL);
  }

  // Método para buscar um cliente por ID
  getClienteById(id: number): Observable<Cliente> {
    return this.http.get<Cliente>(`${this.FULL_URL}/${id}`);
  }

  // Método para criar um novo cliente
  createCliente(cliente: Cliente, conta: number|undefined): Observable<Cliente> {
    return this.http.post<Cliente>(`${this.FULL_URL}/novo/${conta}`, cliente);
  }

  // Método para atualizar um cliente existente
  updateCliente(id: number, cliente: Cliente): Observable<Cliente> {
    return this.http.put<Cliente>(`${this.FULL_URL}/${id}`, cliente);
  }

  // Método para deletar um cliente
  deleteCliente(id: number): Observable<void> {
    return this.http.delete<void>(`${this.FULL_URL}/${id}`);
  }

}
