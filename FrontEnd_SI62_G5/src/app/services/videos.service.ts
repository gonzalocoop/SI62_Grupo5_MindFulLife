import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Videos } from '../models/Videos';

const base_url=environment.base


@Injectable({
  providedIn: 'root'
})
export class VideosService {

  private url=`${base_url}/videos`
  private listaCambio=new Subject<Videos[]>()
  constructor(private http:HttpClient) { }

  list(){
    return this.http.get<Videos[]>(this.url)
  }

  //insert, get y set para el registrar
  insert(cu:Videos){
    return this.http.post(this.url,cu);
  }
  //get y set
  getList(){
    return this.listaCambio.asObservable();
  }

  setList(listaNueva:Videos[]){
    this.listaCambio.next(listaNueva); 
  }

  delete(id:number){
    return this.http.delete(`${this.url}/${id}`);
  }

  listId(id:number){
    return this.http.get<Videos>(`${this.url}/${id}`)
  }
  update(d:Videos){
    return this.http.put(this.url,d)
  }
}
