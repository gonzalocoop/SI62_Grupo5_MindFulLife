import {  Component, OnInit, ViewChild } from '@angular/core';
import { Cursos } from '../../../models/Cursos';
import { CursosService } from '../../../services/cursos.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { catchError, of } from 'rxjs';
import { CompartiruserService } from '../../../services/compartiruser.service';
import { Usuarios } from '../../../models/Usuarios';
import { CursosUsuariosService } from '../../../services/cursos-usuarios.service';
import { CronogramasService } from '../../../services/cronogramas.service';

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

  selectedUser: Usuarios | null = null;
  // Inyecta el servicio `CursosService` para acceder a los datos de cursos
  constructor(private cS: CursosService,  private usuariocompartido: CompartiruserService, private cuS: CursosUsuariosService, private cR: CronogramasService) {}

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
    // Suscribirse al usuario seleccionado desde el servicio compartido
    this.usuariocompartido.selectedUser$.subscribe(user => {
      this.selectedUser = user; // Asignar el usuario seleccionado a la variable
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
        this.mensaje='No se puede eliminar, tiene componentes ligados a este curso';
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


  
  postular(id: number): void {
    let usuario_id: number;

    if (this.selectedUser?.id !== undefined) {
        usuario_id = this.selectedUser.id; // Asignar si no es undefined
    } else {
        usuario_id = 0; // Manejo del caso donde `id` es undefined
    }

    // Registrar el usuario en el curso
    this.cuS.registrarUsuarioEnCurso(id, usuario_id).pipe(
        catchError((error) => {
            // Manejo de errores basado en el código de estado
            if (error.status === 409) { // El código que devuelve el backend para conflictos
                this.mensaje = 'Ya estás registrado en este curso';
            } else {
                this.mensaje = 'Error al intentar registrar en el curso'; // Mensaje genérico para otros errores
            }
            this.ocultarMensaje(); // Llamar a la función para ocultar el mensaje después de un tiempo
            return of(null);
        })
    ).subscribe(response => {
        // Si la operación fue exitosa
        if (response) {
            this.mensaje = '¡Registrado con éxito al curso!';
            this.ocultarMensaje();

            // Aquí obtienes el curso_usuario usando los ids
            this.cuS.listSegunUsuarioYCurso(id, usuario_id).pipe(
                catchError(error => {
                    this.mensaje = 'Error al obtener el curso usuario';
                    this.ocultarMensaje();
                    return of(null);
                })
            ).subscribe(cursoUsuario => {
                if (cursoUsuario) {
                    // Ahora tienes el `cursoUsuario` con su `id`
                    const idCursoUsuario = cursoUsuario.id;

                    // Llamas a la función para generar los cronogramas
                    this.cR.generar(idCursoUsuario).subscribe(response => {
                        // Aquí puedes manejar lo que quieras hacer después de generar el cronograma
                        console.log('Cronogramas generados', response);
                    });
                } else {
                    this.mensaje = 'No se encontró el curso usuario';
                    this.ocultarMensaje();
                }
            });

            // Refrescas los cursos, actualizas paginación, etc.
            this.cS.list().subscribe(data => {
                this.cursos = data;
                this.updatePagedCursos();
                this.paginator.pageIndex = 0; // Reiniciar a la primera página
                this.paginator.length = this.cursos.length; // Actualizar la longitud del paginador
            });
        }
    });
}
}