import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Movimentacao } from '../../../../../shared/models/movimentacao';

@Injectable({
  providedIn: 'root'
})
export class MovimentacaoApiService {
  readonly BASE_URL = environment.IBMBankApi.baseUrl;
  readonly ENDPOINT = environment.IBMBankApi.endpoints.movimentacoes;
  readonly FULL_URL = `${this.BASE_URL}/${this.ENDPOINT}`;

  constructor(private http: HttpClient) { }

  // Método para buscar todas as movimentações
  getAllMovimentacoes(): Observable<Movimentacao[]> {
    return this.http.get<Movimentacao[]>(this.FULL_URL);
  }

  // Método para buscar uma movimentação por ID
  getMovimentacaoById(id: number): Observable<Movimentacao> {
    return this.http.get<Movimentacao>(`${this.FULL_URL}/${id}`);
  }

  // Método para criar uma nova movimentação
  createMovimentacao(movimentacao: Movimentacao): Observable<Movimentacao> {
    return this.http.post<Movimentacao>(this.FULL_URL, movimentacao);
  }

  // Método para atualizar uma movimentação existente
  updateMovimentacao(id: number, movimentacao: Movimentacao): Observable<Movimentacao> {
    return this.http.put<Movimentacao>(`${this.FULL_URL}/${id}`, movimentacao);
  }

  // Método para deletar uma movimentação
  deleteMovimentacao(id: number): Observable<void> {
    return this.http.delete<void>(`${this.FULL_URL}/${id}`);
  }

  // Método para listar movimentações por filtros
  listarMovimentacoesPorConta(
    contaId: number,
    tipo?: string,
    dataInicio?: string,
    dataFim?: string,
    statusPagamento?: string
  ): Observable<Movimentacao[]> {
    let params = new HttpParams();

    if (tipo) {
      params = params.set('tipo', tipo);
    }
    if (dataInicio) {
      params = params.set('dataInicio', dataInicio);
    }
    if (dataFim) {
      params = params.set('dataFim', dataFim);
    }
    if (statusPagamento) {
      params = params.set('statusPagamento', statusPagamento);
    }

    return this.http.get<Movimentacao[]>(`${this.FULL_URL}/conta/${contaId}`, { params });
  }
}
