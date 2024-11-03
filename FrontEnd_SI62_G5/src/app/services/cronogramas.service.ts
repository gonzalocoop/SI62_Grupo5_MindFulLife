import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs';
import { Cronogramas } from '../models/Cronogramas';

const base_url=environment.base


@Injectable({
  providedIn: 'root'
})
export class CronogramasService {

  private url=`${base_url}/cronogramas`
  private listaCambio=new Subject<Cronogramas[]>()
  constructor(private http:HttpClient) { }

  list(){
    return this.http.get<Cronogramas[]>(this.url)
  }

  //insert, get y set para el registrar
  insert(c:Cronogramas){
    return this.http.post(this.url,c);
  }
  //get y set
  getList(){
    return this.listaCambio.asObservable();
  }

  setList(listaNueva:Cronogramas[]){
    this.listaCambio.next(listaNueva); 
  }

  delete(id:number){
    return this.http.delete(`${this.url}/${id}`);
  }

  listId(id:number){
    return this.http.get<Cronogramas>(`${this.url}/${id}`)
  }
  update(c:Cronogramas){
    return this.http.put(this.url,c)
  }
}
