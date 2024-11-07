import { Injectable } from '@angular/core';
import { UsuarioSuscripciones } from '../models/UsuarioSuscripciones';
import { environment } from '../../environments/environment';
import { Observable, Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { UsuariosTipoSuscripcionDTO } from '../models/UsuariosTipoSuscripcionDTO';
import { RecaudacionSuscripcionDTO } from '../models/RecaudacionSuscripcionDTO';

const base_url=environment.base

@Injectable({
  providedIn: 'root'
})
export class UsuarioSuscripcionesService {

  private url=`${base_url}/usuariossuscripciones`
  private listaCambio=new Subject<UsuarioSuscripciones[]>()
  constructor(private http:HttpClient) { }


  list(){
    return this.http.get<UsuarioSuscripciones[]>(this.url)
  }

  //insert, get y set para el registrar
  insert(us:UsuarioSuscripciones){
    return this.http.post(this.url,us);
  }
  //get y set
  getList(){
    return this.listaCambio.asObservable();
  }

  setList(listaNueva:UsuarioSuscripciones[]){
    this.listaCambio.next(listaNueva); 
  }

  delete(id:number){
    return this.http.delete(`${this.url}/${id}`);
  }

  listId(id:number){
    return this.http.get<UsuarioSuscripciones>(`${this.url}/${id}`)
  }
  update(us:UsuarioSuscripciones){
    return this.http.put(this.url,us)
  }

  usuariosTipoSuscripcion(suscripcion: string): Observable<UsuariosTipoSuscripcionDTO[]> {
    const encodedSuscripcion = encodeURIComponent(suscripcion);
    const urll = `${this.url}/usuariostiposuscripcion?s=${encodedSuscripcion}`;
    return this.http.get<UsuariosTipoSuscripcionDTO[]>(urll);
  }
  recaudacionSuscripciones(suscripcion: string, fechaI: Date, fechaF: Date): Observable<RecaudacionSuscripcionDTO[]> {
    const encodedSuscripcion = encodeURIComponent(suscripcion);
    const fechaInicio = fechaI.toISOString().split('T')[0]; // Convierte la fecha a 'YYYY-MM-DD'
    const fechaFin = fechaF.toISOString().split('T')[0]; // Convierte la fecha a 'YYYY-MM-DD'
    const urll = `${this.url}/recaudacion?nombreSuscripcion=${encodedSuscripcion}&fechaInicio=${fechaInicio}&fechaFin=${fechaFin}`;
    return this.http.get<RecaudacionSuscripcionDTO[]>(urll);
}
}
