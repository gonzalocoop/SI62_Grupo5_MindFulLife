import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Cursos } from '../models/Cursos';
import { Subject } from 'rxjs';

const base_url=environment.base

@Injectable({
  providedIn: 'root'
})
export class CursosService {
  private url=`${base_url}/cursos`
  private listaCambio=new Subject<Cursos[]>()
  constructor(private http:HttpClient) { }

  list(){
    return this.http.get<Cursos[]>(this.url)
  }

  //insert, get y set para el registrar
  insert(cu:Cursos){
    return this.http.post(this.url,cu);
  }
  //get y set
  getList(){
    return this.listaCambio.asObservable();
  }

  setList(listaNueva:Cursos[]){
    this.listaCambio.next(listaNueva); 
  }

  delete(id:number){
    return this.http.delete(`${this.url}/${id}`);
  }

  listId(id:number){
    return this.http.get<Cursos>(`${this.url}/${id}`)
  }
  update(d:Cursos){
    return this.http.put(this.url,d)
  }

}