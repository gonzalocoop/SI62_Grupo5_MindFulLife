import {  Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { catchError, of } from 'rxjs';
import { Sesiones } from '../../../models/Sesiones';
import { SesionesService } from '../../../services/sesiones.service';

@Component({
  selector: 'app-listarsesiones',
  standalone: true,
 // Módulos importados para usar tarjetas, íconos, paginación y enrutamiento en el HTML
 imports: [CommonModule, MatPaginator, MatIconModule, MatCardModule, RouterModule],
  templateUrl: './listarsesiones.component.html',
  styleUrl: './listarsesiones.component.css'
})
export class ListarsesionesComponent implements OnInit{

  mensaje:string="";
  sesiones: Sesiones[] = []; // Arreglo que contiene todos las sesiones
  pagedSesiones: Sesiones[] = []; // Sesiones de la página actual para mostrar en las tarjetas
  
  @ViewChild(MatPaginator) paginator!: MatPaginator; // Referencia al paginador para controlarlo

  // Inyecta el servicio `SesionesService` para acceder a los datos de sesiones
  constructor(private sS: SesionesService) {}

  ngOnInit(): void {
    // Suscripción para obtener la lista completa de sesiones y actualizar `pagedSesiones`
    this.sS.list().subscribe(data => {
      this.sesiones = data;       // Guarda todas las sesiones obtenidos
      this.updatePagedSesiones();  // Muestra solo las sesiones de la página actual
    });

    // Escucha actualizaciones en el servicio y vuelve a cargar `sesiones` y `pagedSesiones`
    this.sS.getList().subscribe(data => {
      this.sesiones = data;       // Guarda todas las sesiones actualizados
      this.updatePagedSesiones(); // Actualiza la vista con la página actual
    });
  }

  // Después de que la vista se inicializa, suscríbete a cambios de página del paginador
  ngAfterViewInit(): void {
    // Actualiza las sesiones que se muestran en cada cambio de página
    this.paginator.page.subscribe(() => this.updatePagedSesiones());
  }

  // Actualiza las sesiones visibles según el índice de página y el tamaño de página del paginador
  updatePagedSesiones(): void {
    if (this.paginator) { // Verificar que el paginador esté definido
      const startIndex = this.paginator.pageIndex * this.paginator.pageSize; // Calcular índice inicial
      const endIndex = startIndex + this.paginator.pageSize; // Calcular índice final
      this.pagedSesiones = this.sesiones.slice(startIndex, endIndex); // Extraer sesiones paginadas
    } else {
      // Mostrar las primeras 10 sesiones si el paginador no está disponible
      this.pagedSesiones = this.sesiones.slice(0, 10);
    }
  }

  // Llama al servicio para eliminar una ssion por ID y actualiza la lista de sesiones y la vista de la página actual
  eliminar(id: number): void {
    this.sS.delete(id).pipe(
      catchError((error)=>{
        this.mensaje='No se puede eliminar, tiene videos registrados en esta sesión';
        this.ocultarMensaje()
        return of(null);
      })
    ).subscribe(() => {
      // Actualizar la lista después de la eliminación
      this.sS.list().subscribe(data => {
        this.sesiones = data;
        this.updatePagedSesiones();
        // Resetear el paginador a la primera página
        this.paginator.pageIndex = 0; // Reiniciar a la primera página
        this.paginator.length = this.sesiones.length; // Actualizar la longitud del paginador
      });
    });
  }
  ocultarMensaje(){
    setTimeout(()=>{
      this.mensaje='';
    }, 3000);
  }
}
