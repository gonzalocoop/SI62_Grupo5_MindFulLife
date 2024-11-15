import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';  // Importa CommonModule
import { ChartDataset, ChartOptions, ChartType } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';
import { CursosService } from '../../../services/cursos.service';

@Component({
  selector: 'app-top5cursoscantsesionescategoriasesiones',
  standalone: true,
  imports: [BaseChartDirective, CommonModule],  // Asegúrate de incluir CommonModule aquí
  templateUrl: './top5cursoscantsesionescategoriasesiones.component.html',
  styleUrls: ['./top5cursoscantsesionescategoriasesiones.component.css']
})
export class Top5cursoscantsesionescategoriasesionesComponent implements OnInit {
  barChartOptions: ChartOptions = {
    responsive: true,
    scales: {
      y: {
        beginAtZero: true,  // Para que el eje Y comience desde cero
      }
    },
  };

  barChartLabels: string[] = [];
  barChartType: ChartType = 'bar';
  barChartLegend = true;
  barChartData: ChartDataset[] = [];
  hayDatos: boolean = false; // Controlador para mostrar o no el gráfico

  constructor(private cS: CursosService) {}

  ngOnInit(): void {
    this.cS.top5CantSesiones().subscribe((data) => {
      // Si los datos están vacíos, establecemos hayDatos como false
      if (data && data.length > 0) {
        this.hayDatos = true;

        // Rellenar las etiquetas con los nombres de los cursos
        this.barChartLabels = data.map(item => item.curso);

        // Colores dinámicos basados en la categoría del curso
        const backgroundColors = data.map(item => {
          switch(item.categoria) {
            case 'Bajo': return '#4caf50'; // verde para Básico
            case 'Medio': return '#ff9800'; // naranja para Intermedio
            case 'Alto': return '#f44336'; // rojo para Avanzado
            default: return '#2196f3'; // azul para otros
          }
        });

        const borderColors = data.map(item => '#000000'); // Color de borde negro

        // Configuración de los datos del gráfico
        this.barChartData = [
          {
            data: data.map(item => item.cantidadSesiones),
            label: 'Cantidad de Sesiones',
            backgroundColor: backgroundColors, // Asignar color por categoría
            borderColor: borderColors,
            borderWidth: 1
          },
          {
            data: data.map(item => item.duracionCurso),
            label: 'Duración del Curso (días)',
            backgroundColor: ['#f08e79'],
            borderColor: '#2196f3', // Color azul para duración
            borderWidth: 2,
            borderDash: [5, 5], // Líneas punteadas para la duración
          }
        ];
      } else {
        this.hayDatos = false; // Si no hay datos, mostrar el mensaje de error
      }
    });
  }
}
