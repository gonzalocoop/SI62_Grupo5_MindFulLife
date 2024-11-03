import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router, RouterModule } from '@angular/router';
import { CursosUsuarios } from '../../../models/CursosUsuarios';
import { CursosUsuariosService } from '../../../services/cursos-usuarios.service';
import { CommonModule } from '@angular/common';
import { Usuarios } from '../../../models/Usuarios';
import { CompartiruserService } from '../../../services/compartiruser.service';


@Component({
  selector: 'app-vercursos',
  standalone: true,
  imports: [RouterModule,CommonModule],
  templateUrl: './vercursos.component.html',
  styleUrl: './vercursos.component.css'
})
export class VercursosComponent implements OnInit{
  cursousuario: CursosUsuarios = new CursosUsuarios(); // Objeto para almacenar los detalles del curso
  id: number = 0; // ID del curso
  selectedUser: Usuarios | null = null; // Variable para almacenar el usuario seleccionado


  // Inyecta los servicios necesarios en el constructor
  constructor(private route: ActivatedRoute, private cuS: CursosUsuariosService, private router: Router,    private usuariocompartido: CompartiruserService // Inyecta el servicio compartido
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

  // Función para obtener el curso por ID desde el servicio
  obtenerCurso(): void {
    this.cuS.listId(this.id).subscribe((data: CursosUsuarios) => {
      this.cursousuario = data; // Asignar el curso obtenido al objeto curso
    });
  }
}
