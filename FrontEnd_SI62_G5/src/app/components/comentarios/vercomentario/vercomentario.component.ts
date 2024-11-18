import { Component } from '@angular/core';
import { ActivatedRoute, Params, Router, RouterModule } from '@angular/router';
import { Comentarios } from '../../../models/Comentarios';
import { ComentariosService } from '../../../services/comentarios.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-vercomentario',
  standalone: true,
  imports: [RouterModule,CommonModule],
  templateUrl: './vercomentario.component.html',
  styleUrl: './vercomentario.component.css'
})
export class VercomentarioComponent {
  comentarios: Comentarios = new Comentarios(); // Objeto para almacenar los detalles del comentario
  id: number = 0; // ID del curso

  // Inyecta los servicios necesarios en el constructor
  constructor(private route: ActivatedRoute, private cS: ComentariosService, private router:Router) {}

  ngOnInit(): void {
    // Suscribirse a los parámetros de la ruta para obtener el ID del comentario
    this.route.params.subscribe((data: Params) => {
      this.id = data['id']; // Obtener el ID del comentario de los parámetros
      this.obtenerComentario(); // Llamar a la función para obtener el comentario
    });
  }

  // Función para obtener el comentario por ID desde el servicio
  obtenerComentario(): void {
    this.cS.listId(this.id).subscribe((data: Comentarios) => {
      this.comentarios = data; // Asignar el comentario obtenido al objeto comentario
    });
  }
}
