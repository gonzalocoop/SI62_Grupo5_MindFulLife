import { Component, OnInit,ViewChild } from '@angular/core';
import { ActivatedRoute, Params, Router, RouterModule } from '@angular/router';
import { CursosUsuarios } from '../../../models/CursosUsuarios';
import { CursosUsuariosService } from '../../../services/cursos-usuarios.service';
import { CommonModule } from '@angular/common';
import { Usuarios } from '../../../models/Usuarios';
import { CompartiruserService } from '../../../services/compartiruser.service';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { Sesiones } from '../../../models/Sesiones';
import { SesionesService } from '../../../services/sesiones.service';


@Component({
  selector: 'app-vercursos',
  standalone: true,
  imports: [RouterModule,CommonModule, MatCardModule, MatIconModule],
  templateUrl: './vercursos.component.html',
  styleUrl: './vercursos.component.css'
})
export class VercursosComponent implements OnInit{
  cursousuario: CursosUsuarios = new CursosUsuarios(); // Objeto para almacenar los detalles del curso
  id: number = 0; // ID del curso
  selectedUser: Usuarios | null = null; // Variable para almacenar el usuario seleccionado

  sesiones: Sesiones[] = []; // Arreglo que contiene todos los cursos
  pagedSesiones: Sesiones[] = []; // Cursos de la página actual para mostrar en las tarjetas


  // Inyecta los servicios necesarios en el constructor
  constructor(private sS: SesionesService,private route: ActivatedRoute, private cuS: CursosUsuariosService, private router: Router,    private usuariocompartido: CompartiruserService // Inyecta el servicio compartido
  ) {}

  ngOnInit(): void {
    // Suscribirse a los parámetros de la ruta para obtener el ID del curso
    this.route.params.subscribe((data: Params) => {
      this.id = data['id']; // Obtener el ID del curso de los parámetros
      this.obtenerCurso(); // Llamar a la función para obtener el curso
    });

    // Suscribirse al usuario seleccionado desde el servicio compartido
    this.usuariocompartido.selectedUser$.subscribe(user => {
      this.selectedUser = user; // Asignar el usuario seleccionado a la variable
    });

    

    

  }

 

  // Actualiza los cursos visibles según el índice de página y el tamaño de página del paginador
  updatePagedSesiones(): void {
    
      // Mostrar los primeros 10 cursos si el paginador no está disponible
      this.pagedSesiones = this.sesiones.slice(0, 10);
    
  }

  redirectToURL(): void {
    if (this.cursousuario.estado === 'completado') {
      this.router.navigate(['/cursosusuarios/obtenerurl', this.cursousuario.id]);
    } else {
      alert('El curso no ha sido completado aún.');
    }
  }

  // Función para obtener el curso por ID desde el servicio
  obtenerCurso(): void {
    this.cuS.listId(this.id).subscribe((data: CursosUsuarios) => {
      this.cursousuario = data; // Asignar el curso obtenido al objeto curso
      //Obtener sesiones
    this.sS.listPorCurso(this.cursousuario.cur.titulo).subscribe(data => {
      this.sesiones = data;       // Guarda todos los cursos obtenidos
      this.updatePagedSesiones();  // Muestra solo los cursos de la página actual
    })// Escucha actualizaciones en el servicio y vuelve a cargar `cursos` y `pagedCursos`
    this.sS.getList().subscribe(data => {
      this.sesiones = data;       // Guarda todos los cursos actualizados
      this.updatePagedSesiones(); // Actualiza la vista con la página actual
    });
    });
    
  }
}
