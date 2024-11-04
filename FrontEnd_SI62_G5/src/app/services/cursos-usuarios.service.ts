import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs';
import { CursosUsuarios } from '../models/CursosUsuarios';

const base_url=environment.base

@Injectable({
  providedIn: 'root'
})
export class CursosUsuariosService {

  private url=`${base_url}/cursoscsuarios`
  private listaCambio=new Subject<CursosUsuarios[]>()
  constructor(private http:HttpClient) { }

  list(){
    return this.http.get<CursosUsuarios[]>(this.url)
  }

  //insert, get y set para el registrar
  insert(cu:CursosUsuarios){
    return this.http.post(this.url,cu);
  }
  //get y set
  getList(){
    return this.listaCambio.asObservable();
  }

  setList(listaNueva:CursosUsuarios[]){
    this.listaCambio.next(listaNueva); 
  }

  delete(id:number){
    return this.http.delete(`${this.url}/${id}`);
  }

  listId(id:number){
    return this.http.get<CursosUsuarios>(`${this.url}/${id}`)
  }
  update(cu:CursosUsuarios){
    return this.http.put(this.url,cu)
  }

 // Nueva función para registrar un usuario en un curso usando parámetros de consulta
  registrarUsuarioEnCurso(idCurso: number, idUsuario: number) {
  // Construir la URL con los parámetros de consulta
  const urll = `${this.url}/registrarcurso?idCurso=${idCurso}&idUsuario=${idUsuario}`;
  return this.http.post(urll, null); // Se envía null porque no hay cuerpo en la solicitud
    
  }
  
}
