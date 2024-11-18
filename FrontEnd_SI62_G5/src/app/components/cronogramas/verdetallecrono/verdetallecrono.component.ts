import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router, RouterModule } from '@angular/router';
import { Cronogramas } from '../../../models/Cronogramas';
import { CronogramasService } from '../../../services/cronogramas.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-verdetallecrono',
  standalone: true,
  imports: [RouterModule,CommonModule],
  templateUrl: './verdetallecrono.component.html',
  styleUrl: './verdetallecrono.component.css'
})
export class VerdetallecronoComponent implements OnInit{
  Cronogramas: Cronogramas = new Cronogramas(); // Objeto para almacenar los detalles del cronograma
  id: number = 0; // ID del cronograma

  // Inyecta los servicios necesarios en el constructor
  constructor(private route: ActivatedRoute, private cS: CronogramasService, private router: Router) {}

  ngOnInit(): void {
    // Suscribirse a los parámetros de la ruta para obtener el ID del cronograma
    this.route.params.subscribe((data: Params) => {
      this.id = data['id']; // Obtener el ID del cronograma de los parámetros
      this.obtenerCronogramas(); // Llamar a la función para obtener el cronograma
    });
  }

  // Función para obtener el cronograma por ID desde el servicio
  obtenerCronogramas(): void {
    this.cS.listId(this.id).subscribe((data: Cronogramas) => {
      this.Cronogramas = data; // Asignar el cronograma obtenido al objeto cronograma
    });
  }

}
