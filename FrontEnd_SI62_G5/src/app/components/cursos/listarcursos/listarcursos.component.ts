import {  Component, OnInit, ViewChild } from '@angular/core';
import { Cursos } from '../../../models/Cursos';
import { CursosService } from '../../../services/cursos.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { catchError, of } from 'rxjs';

@Component({
  selector: 'app-listarcursos',
  standalone: true,
  // Módulos importados para usar tarjetas, íconos, paginación y enrutamiento en el HTML
  imports: [CommonModule, MatPaginator, MatIconModule, MatCardModule, RouterModule],
  templateUrl: './listarcursos.component.html',
  styleUrls: ['./listarcursos.component.css']
})
export class ListarcursosComponent implements OnInit {
  mensaje:string="";
  cursos: Cursos[] = []; // Arreglo que contiene todos los cursos
  pagedCursos: Cursos[] = []; // Cursos de la página actual para mostrar en las tarjetas
  
  @ViewChild(MatPaginator) paginator!: MatPaginator; // Referencia al paginador para controlarlo

  // Inyecta el servicio `CursosService` para acceder a los datos de cursos
  constructor(private cS: CursosService) {}

  ngOnInit(): void {
    // Suscripción para obtener la lista completa de cursos y actualizar `pagedCursos`
    this.cS.list().subscribe(data => {
      this.cursos = data;       // Guarda todos los cursos obtenidos
      this.updatePagedCursos();  // Muestra solo los cursos de la página actual
    });

    // Escucha actualizaciones en el servicio y vuelve a cargar `cursos` y `pagedCursos`
    this.cS.getList().subscribe(data => {
      this.cursos = data;       // Guarda todos los cursos actualizados
      this.updatePagedCursos(); // Actualiza la vista con la página actual
    });
  }

  // Después de que la vista se inicializa, suscríbete a cambios de página del paginador
  ngAfterViewInit(): void {
    // Actualiza los cursos que se muestran en cada cambio de página
    this.paginator.page.subscribe(() => this.updatePagedCursos());
  }

  // Actualiza los cursos visibles según el índice de página y el tamaño de página del paginador
  updatePagedCursos(): void {
    if (this.paginator) { // Verificar que el paginador esté definido
      const startIndex = this.paginator.pageIndex * this.paginator.pageSize; // Calcular índice inicial
      const endIndex = startIndex + this.paginator.pageSize; // Calcular índice final
      this.pagedCursos = this.cursos.slice(startIndex, endIndex); // Extraer cursos paginados
    } else {
      // Mostrar los primeros 10 cursos si el paginador no está disponible
      this.pagedCursos = this.cursos.slice(0, 10);
    }
  }

  // Llama al servicio para eliminar un curso por ID y actualiza la lista de cursos y la vista de la página actual
  eliminar(id: number): void {
    this.cS.delete(id).pipe(
      catchError((error)=>{
        this.mensaje='No se puede eliminar, tiene usuarios registrados en esta suscripcion';
        this.ocultarMensaje()
        return of(null);
      })
    ).subscribe(() => {
      // Actualizar la lista después de la eliminación
      this.cS.list().subscribe(data => {
        this.cursos = data;
        this.updatePagedCursos();
        // Resetear el paginador a la primera página
        this.paginator.pageIndex = 0; // Reiniciar a la primera página
        this.paginator.length = this.cursos.length; // Actualizar la longitud del paginador
      });
    });
  }
  ocultarMensaje(){
    setTimeout(()=>{
      this.mensaje='';
    }, 3000);
  }
}