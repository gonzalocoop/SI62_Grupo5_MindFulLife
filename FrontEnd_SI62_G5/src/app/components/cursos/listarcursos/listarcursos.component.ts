import { Component, OnInit,ViewChild } from '@angular/core';
import { Cursos } from '../../../models/Cursos';
import { MatTableDataSource } from '@angular/material/table';
import { CursosService } from '../../../services/cursos.service';
import { MatTableModule } from '@angular/material/table';
//Para lo de fecha en dd/mm/yyyy
import { CommonModule } from '@angular/common';  // Asegúrate de importar CommonModule
//Para el paginator
import { MatPaginator } from '@angular/material/paginator'; //Preferi usar este, en vez de MatPaginatorModule
import { MatIconModule } from '@angular/material/icon';
import { RouterModule } from '@angular/router';
@Component({
  selector: 'app-listarcursos',
  standalone: true,
  imports: [MatTableModule,CommonModule,MatPaginator,MatIconModule,RouterModule],
  templateUrl: './listarcursos.component.html',
  styleUrl: './listarcursos.component.css'
})
export class ListarcursosComponent {
  dataSource: MatTableDataSource<Cursos> = new MatTableDataSource();
  displayedColumns:string[]=['c1','c2','c3','c4','accion01','accion02'] //para indicar que es un conjunto o arreglo
  @ViewChild(MatPaginator) paginator!: MatPaginator;  // Referencia al paginator
  //ngOnInit: El segundo metodo en ejecutarse, luego del constructor, segun angular . material
  constructor(private cS:CursosService){}  //Inyectamos service
  
  ngOnInit(): void {  //subscribe: patron de diseño de software para devolver datos, en este caso de 
      this.cS.list().subscribe(data=>{
        this.dataSource=new MatTableDataSource(data)
        this.dataSource.paginator = this.paginator;  // Asignar el paginator a la dataSource aquí
      })
      this.cS.getList().subscribe(data=>{
        this.dataSource=new MatTableDataSource(data)
        this.dataSource.paginator = this.paginator;  // Asignar el paginator a la dataSource aquí
      });
  }
  //para que siempre funcione el paginator
  ngAfterViewInit(): void {
    // Asegúrate de que el paginador se aplica después de que se inicialice la vista
    this.dataSource.paginator = this.paginator;
  }

  eliminar(id:number){
    this.cS.delete(id).subscribe((data)=>{ //Despues de eliminar, me devuelve la data con el elemento ya eliminado, se usa subscribe en las funciones que tienen un return
      this.cS.list().subscribe((data)=>{
        this.cS.setList(data);
      })
    })
  }
}
