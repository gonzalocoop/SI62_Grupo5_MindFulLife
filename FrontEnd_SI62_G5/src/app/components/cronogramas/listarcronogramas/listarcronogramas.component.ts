import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { catchError, of } from 'rxjs';
import { Cronogramas } from '../../../models/Cronogramas';
import { CronogramasService } from '../../../services/cronogramas.service';

@Component({
  selector: 'app-listarcronogramas',
  standalone: true,
  imports: [
    CommonModule,
    MatPaginator,
    MatIconModule,
    MatCardModule,
    RouterModule,
  ],
  templateUrl: './listarcronogramas.component.html',
  styleUrl: './listarcronogramas.component.css',
})

export class ListarcronogramasComponent implements OnInit {
  mensaje: string = '';
  Cronogramas: Cronogramas[] = []; // Arreglo que contiene todos los Cronogramas
  pagedCronogramas: Cronogramas[] = []; // Cursos de la página actual para mostrar en las tarjetas

  @ViewChild(MatPaginator) paginator!: MatPaginator; // Referencia al paginador para controlarlo

  constructor(private cS: CronogramasService) {}

  ngOnInit(): void {
    this.cS.list().subscribe((data) => {
      this.Cronogramas = data; 
      this.updatePagedCronogramas(); 
    });

    this.cS.getList().subscribe((data) => {
      this.Cronogramas = data; 
      this.updatePagedCronogramas(); 
    });
  }

  // Después de que la vista se inicializa, suscríbete a cambios de página del paginador
  ngAfterViewInit(): void {
    this.paginator.page.subscribe(() => this.updatePagedCronogramas());
  }

  updatePagedCronogramas(): void {
    if (this.paginator) {
      // Verificar que el paginador esté definido
      const startIndex = this.paginator.pageIndex * this.paginator.pageSize; // Calcular índice inicial
      const endIndex = startIndex + this.paginator.pageSize; // Calcular índice final
      this.pagedCronogramas = this.Cronogramas.slice(startIndex, endIndex); // Extraer cronogramas paginados
    } else {
      // Mostrar los primeros 10 cursos si el paginador no está disponible
      this.pagedCronogramas = this.Cronogramas.slice(0, 10);
    }
  }

  eliminar(id: number): void {
    this.cS
      .delete(id)
      .pipe(
        catchError((error) => {
          this.mensaje =
            'No se puede eliminar, tiene cronogramas registrados en esta sesión';
          this.ocultarMensaje();
          return of(null);
        })
      )
      .subscribe(() => {
        // Actualizar la lista después de la eliminación
        this.cS.list().subscribe((data) => {
          this.Cronogramas = data;
          this.updatePagedCronogramas();
          // Resetear el paginador a la primera página
          this.paginator.pageIndex = 0; // Reiniciar a la primera página
          this.paginator.length = this.Cronogramas.length; // Actualizar la longitud del paginador
        });
      });
  }

  ocultarMensaje() {
    setTimeout(() => {
      this.mensaje = '';
    }, 3000);
  }
}