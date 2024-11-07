import { Injectable } from "@angular/core";
import { environment } from "../../environments/environment";
import { Observable, Subject } from "rxjs";
import { Comentarios } from "../models/Comentarios";
import { HttpClient } from "@angular/common/http";
import { CursoCantComentariosDTO } from "../models/CursoCantComentariosDTO";

const base_url=environment.base

@Injectable({
  providedIn: 'root'
})
export class ComentariosService {

  private url=`${base_url}/comentarios`
  private listaCambio=new Subject<Comentarios[]>()
  constructor(private http:HttpClient) { }

  list(){
    return this.http.get<Comentarios[]>(this.url)
  }

  //insert, get y set para el registrar
  insert(co:Comentarios){
    return this.http.post(this.url,co);
  }
  //get y set
  getList(){
    return this.listaCambio.asObservable();
  }

  setList(listaNueva:Comentarios[]){
    this.listaCambio.next(listaNueva); 
  }

  delete(id:number){
    return this.http.delete(`${this.url}/${id}`);
  }

  listId(id:number){
    return this.http.get<Comentarios>(`${this.url}/${id}`)
  }
  update(d:Comentarios){
    return this.http.put(this.url,d)
  }

  // Función para buscar sesiones por título
  listPorSesion(titulo: string): Observable<any> {
    // Asegúrate de codificar el título para que sea seguro para la URL
    const encodedTitulo = encodeURIComponent(titulo);
    const urll = `${this.url}/sesiontitulocomentario?tituloSesion=${encodedTitulo}`;
    return this.http.get(urll);
  }

  top3Cursos():Observable<CursoCantComentariosDTO[]>{
    return this.http.get<CursoCantComentariosDTO[]>(`${this.url}/top3cursosmascomentarios`);
  }

  listarMalosComentarios(titulo: string): Observable<Comentarios[]> {
    // Asegúrate de codificar el título para que sea seguro para la URL
    const encodedTitulo = encodeURIComponent(titulo);
    const urll = `${this.url}/listarmaloscomentarios?titulo=${encodedTitulo}`;
    return this.http.get<Comentarios[]>(urll);
  }
}
