import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ContaCredito, ContaCreditoToCreate } from 'src/shared/models/contaCredito';

@Injectable({
  providedIn: 'root'
})
export class ContaCreditoApiService {
  readonly BASE_URL = environment.IBMBankApi.baseUrl;
  readonly ENDPOINT = environment.IBMBankApi.endpoints.contasCredito;
  readonly FULL_URL = `${this.BASE_URL}/${this.ENDPOINT}`;

  constructor(private http: HttpClient) { }

  getContaCreditoById(id: number): Observable<ContaCredito> {
    return this.http.get<ContaCredito>(`${this.FULL_URL}/${id}`);
  }

  createContaCredito(contaCredito: ContaCredito, idConta:number): Observable<ContaCreditoToCreate> {
    return this.http.post<ContaCreditoToCreate>(`${this.FULL_URL}/nova/${idConta}`, contaCredito);
  }

}
