import { CommonModule } from '@angular/common';
import { Component, ViewChild } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { RouterModule } from '@angular/router';
import { Roles } from '../../../models/Roles';
import { RolesService } from '../../../services/roles.service';

@Component({
  selector: 'app-listarroles',
  standalone: true,
  imports: [MatTableModule,CommonModule,MatPaginator,MatIconModule,RouterModule],
  templateUrl: './listarroles.component.html',
  styleUrl: './listarroles.component.css'
})
export class ListarrolesComponent {
  dataSource: MatTableDataSource<Roles>= new MatTableDataSource();
  displayedColumns:string[]=['c1','c2', 'accion01', 'accion02']
  @ViewChild(MatPaginator) paginator!:MatPaginator;

  constructor(private cS:RolesService){}

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
