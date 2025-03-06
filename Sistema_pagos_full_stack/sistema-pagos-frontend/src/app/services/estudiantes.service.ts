import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Estudiante, Pago } from '../models/estudiantes.model';
import { environment } from '../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class EstudiantesService {

  constructor(private http: HttpClient) { }

  backendHost: any = "http://localhost:8081"


  public getAllPagos(): Observable<Array<Pago>> {
    return this.http.get<Array<Pago>>(`${this.backendHost}/pagos`);
  }

  public getAllEstudiantes(): Observable<Array<Estudiante>> {
    return this.http.get<Array<Estudiante>>(`${this.backendHost}/estudiantes`);
  }

  public getPagosDeEstudiante(codigo: string): Observable<Array<Pago>> {
    return this.http.get<Array<Pago>>(`${this.backendHost}/estudiantes/${codigo}/pagos`);
  }

  public guardarPago(formData: any): Observable<Pago> {
    return this.http.post<Pago>(`${this.backendHost}/pagos`, formData);
  }
}
