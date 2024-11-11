import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Conta } from '../../../../../shared/models/conta';

@Injectable({
  providedIn: 'root'
})
export class ContaApiService {
  readonly BASE_URL = environment.IBMBankApi.baseUrl;
  readonly ENDPOINT = environment.IBMBankApi.endpoints.contas;
  readonly FULL_URL = `${this.BASE_URL}/${this.ENDPOINT}`;

  constructor(private http: HttpClient) { }

  // Método para buscar todas as contas
  getAllContas(): Observable<Conta[]> {
    return this.http.get<Conta[]>(this.FULL_URL);
  }

  // Método para buscar uma conta por ID
  getContaById(id: number): Observable<Conta> {
    return this.http.get<Conta>(`${this.FULL_URL}/${id}`);
  }

  // Método para criar uma nova conta
  createConta(conta: Conta): Observable<Conta> {
    return this.http.post<Conta>(this.FULL_URL, conta);
  }

  // Método para atualizar uma conta existente
  updateConta(id: number, conta: Conta): Observable<Conta> {
    return this.http.put<Conta>(`${this.FULL_URL}/${id}`, conta);
  }

  // Método para deletar uma conta
  deleteConta(id: number): Observable<void> {
    return this.http.delete<void>(`${this.FULL_URL}/${id}`);
  }
}
