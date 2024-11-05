import { Injectable } from "@angular/core";
import { environment } from "../../environments/environment";
import { Subject } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { VideosFavoritos } from "../models/VideosFavoritos";


const base_url=environment.base


@Injectable({
  providedIn: 'root'
})
export class VideosFavoritosService {

    private url=`${base_url}/videosfavoritos`
    private listaCambio=new Subject<VideosFavoritos[]>()
    constructor(private http:HttpClient) { }
  
    list(){
      return this.http.get<VideosFavoritos[]>(this.url)
    }
  
    //insert, get y set para el registrar
    insert(fS:VideosFavoritos){
      return this.http.post(this.url,fS);
    }
    //get y set
    getList(){
      return this.listaCambio.asObservable();
    }
  
    setList(listaNueva:VideosFavoritos[]){
      this.listaCambio.next(listaNueva); 
    }
  
    delete(id:number){
      return this.http.delete(`${this.url}/${id}`);
    }
  
    listId(id:number){
      return this.http.get<VideosFavoritos>(`${this.url}/${id}`)
    }
    update(fS:VideosFavoritos){
      return this.http.put(this.url,fS)
    }
  }
  