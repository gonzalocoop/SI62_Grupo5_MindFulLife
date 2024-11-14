import { Component, OnInit } from '@angular/core';
import { ChartDataset, ChartOptions, ChartType } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';
import { CursosUsuariosService } from '../../../services/cursos-usuarios.service';
import { Chart, registerables } from 'chart.js';

Chart.register(...registerables);

@Component({
  selector: 'app-cantidadgeneralcursosusuarioscompletados',
  standalone: true,
  imports: [BaseChartDirective],
  templateUrl: './cantidadgeneralcursosusuarioscompletados.component.html',
  styleUrls: ['./cantidadgeneralcursosusuarioscompletados.component.css']
})
export class CantidadgeneralcursosusuarioscompletadosComponent implements OnInit {
  // Opciones básicas del gráfico
  barChartOptions: ChartOptions = {
    responsive: true,
    plugins: {
      tooltip: {
        callbacks: {
          // Formateamos el valor del tooltip para que tenga el símbolo de %
          label: function(context) {
            const value = context.raw as number;  // Asertar que es un número
            return value.toFixed(2) + '%'; // Agrega el símbolo de porcentaje
          }
        }
      }
    }
  };

  // Etiquetas del gráfico
  barChartLabels: string[] = ['Completados', 'No Completados'];

  // Tipo de gráfico: Pie
  barChartType: ChartType = 'pie';

  // Datos del gráfico
  barChartLegend = true;
  barChartData: ChartDataset[] = [];

  constructor(private cuS: CursosUsuariosService) {}

  ngOnInit(): void {
    // Obtenemos los datos del servicio
    this.cuS.cantidadTotaCursosCompletadosYNo().subscribe((data) => {
      // Extraemos las cantidades de los cursos completados y no completados, asegurándonos de que sean números
      const cursosCompletados = Number(data.find(item => item.estado === 'completado')?.cantidad) || 0;
      const cursosNoCompletados = Number(data.find(item => item.estado === 'no completado')?.cantidad) || 0;

      // Calcular el total de cursos
      const totalCursos = cursosCompletados + cursosNoCompletados;

      // Evitar la división por cero
      if (totalCursos > 0) {
        // Calcular los porcentajes
        const porcentajeCompletados = (cursosCompletados / totalCursos) * 100;
        const porcentajeNoCompletados = (cursosNoCompletados / totalCursos) * 100;

        // Asignamos los datos al gráfico
        this.barChartData = [
          {
            data: [porcentajeCompletados, porcentajeNoCompletados],
            backgroundColor: ['#4CAF50', '#f44336'], // Colores para completado y no completado
            borderColor: ['#fff', '#fff'],
            borderWidth: 1,
          },
        ];
      } else {
        // Si no hay cursos, se asignan valores por defecto (0%)
        this.barChartData = [
          {
            data: [0, 0],
            backgroundColor: ['#4CAF50', '#f44336'],
            borderColor: ['#fff', '#fff'],
            borderWidth: 1,
          },
        ];
      }
    });
  }
}
