import { Component, OnInit } from '@angular/core';
import { ChartDataset, ChartOptions, ChartType } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';
import { CursosUsuariosService } from '../../../services/cursos-usuarios.service';
import { CommonModule } from '@angular/common';
import { Chart, registerables } from 'chart.js';

Chart.register(...registerables);

@Component({
  selector: 'app-cantidadgeneralcursosusuarioscompletados',
  standalone: true,
  imports: [BaseChartDirective, CommonModule],  // Asegúrate de importar CommonModule
  templateUrl: './cantidadgeneralcursosusuarioscompletados.component.html',
  styleUrls: ['./cantidadgeneralcursosusuarioscompletados.component.css']
})
export class CantidadgeneralcursosusuarioscompletadosComponent implements OnInit {
  barChartOptions: ChartOptions = {
    responsive: true,
    plugins: {
      tooltip: {
        callbacks: {
          label: function (context) {
            const value = context.raw as number;
            return value.toFixed(2) + '%';
          }
        }
      }
    }
  };

  barChartLabels: string[] = ['Completados', 'No Completados'];
  barChartType: ChartType = 'pie';
  barChartLegend = true;
  barChartData: ChartDataset[] = [];

  // Variable para controlar si no hay datos
  noData: boolean = false;

  constructor(private cuS: CursosUsuariosService) {}

  ngOnInit(): void {
    this.cuS.cantidadTotaCursosCompletadosYNo().subscribe((data) => {
      const cursosCompletados = Number(data.find(item => item.estado === 'completado')?.cantidad) || 0;
      const cursosNoCompletados = Number(data.find(item => item.estado === 'no completado')?.cantidad) || 0;
      const totalCursos = cursosCompletados + cursosNoCompletados;

      if (totalCursos > 0) {
        const porcentajeCompletados = (cursosCompletados / totalCursos) * 100;
        const porcentajeNoCompletados = (cursosNoCompletados / totalCursos) * 100;

        this.barChartData = [
          {
            data: [porcentajeCompletados, porcentajeNoCompletados],
            backgroundColor: ['#4CAF50', '#f44336'],
            borderColor: ['#fff', '#fff'],
            borderWidth: 1,
          },
        ];

        // Asegúrate de que no se muestra el mensaje de error si hay datos
        this.noData = false;
      } else {
        this.barChartData = [
          {
            data: [0, 0],
            backgroundColor: ['#4CAF50', '#f44336'],
            borderColor: ['#fff', '#fff'],
            borderWidth: 1,
          },
        ];

        // Si no hay datos, muestra el mensaje de error
        this.noData = true;
      }
    });
  }
}
