import { Component, OnInit } from '@angular/core';
import { ChartDataset, ChartOptions, ChartType } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';
import { CursosService } from '../../../services/cursos.service';

@Component({
  selector: 'app-top5cursoscantsesionescategoriasesiones',
  standalone: true,
  imports: [BaseChartDirective],
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
  
  constructor(private cS: CursosService) {}

  ngOnInit(): void {
    this.cS.top5CantSesiones().subscribe((data) => {
      // Rellenar las etiquetas con los nombres de los cursos
      this.barChartLabels = data.map(item => item.curso);

      // Colores dinámicos basados en la categoría del curso
      const backgroundColors = data.map(item => {
        switch(item.categoria) {
          case 'Bajo': return '#A1887F'; // verde para Básico
          case 'Medio': return '#FFE57F'; // naranja para Intermedio
          case 'Alto': return '#B2DFDB'; // rojo para Avanzado
          default: return '#A1887F'; // azul para otros
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
          backgroundColor:['#B2DFDB'],
          borderColor: '#000000', // Color azul para duración
          borderWidth: 2,
          borderDash: [5, 5], // Líneas punteadas para la duración
        }
      ];
    });
  }
}
