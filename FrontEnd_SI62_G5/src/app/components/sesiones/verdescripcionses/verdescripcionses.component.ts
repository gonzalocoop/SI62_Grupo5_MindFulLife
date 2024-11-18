import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router, RouterModule } from '@angular/router';
import { SesionesService } from '../../../services/sesiones.service';
import { Sesiones } from '../../../models/Sesiones';


@Component({
  selector: 'app-verdescripcionses',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './verdescripcionses.component.html',
  styleUrl: './verdescripcionses.component.css'
})
export class VerdescripcionsesComponent implements OnInit{
  sesion: Sesiones = new Sesiones(); // Objeto para almacenar los detalles de la sesion
  id: number = 0; // ID de la sesion

  // Inyecta los servicios necesarios en el constructor
  constructor(private route: ActivatedRoute, private sS: SesionesService, private router: Router) {}

  ngOnInit(): void {
    // Suscribirse a los parámetros de la ruta para obtener el ID de la sesion
    this.route.params.subscribe((data: Params) => {
      this.id = data['id']; // Obtener el ID de la sesion de los parámetros
      this.obtenerSesion(); // Llamar a la función para obtener la sesion
    });
  }

  // Función para obtener el curso por ID desde el servicio
  obtenerSesion(): void {
    this.sS.listId(this.id).subscribe((data: Sesiones) => {
      this.sesion = data; // Asignar la sesion obtenido al objeto sesion
    });
  }

}
