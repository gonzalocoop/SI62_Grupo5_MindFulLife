import { Injectable } from '@angular/core';
import { UsuarioSuscripciones } from '../models/UsuarioSuscripciones';
import { environment } from '../../environments/environment';
import { Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';

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
}
