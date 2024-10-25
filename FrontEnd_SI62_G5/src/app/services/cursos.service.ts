import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Cursos } from '../models/Cursos';

const base_url=environment.base

@Injectable({
  providedIn: 'root'
})
export class CursosService {
  private url=`${base_url}/cursos`
  constructor(private http:HttpClient) { }

  list(){
    return this.http.get<Cursos[]>(this.url)
  }
}
