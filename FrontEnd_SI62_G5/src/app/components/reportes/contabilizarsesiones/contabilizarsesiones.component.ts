import { Component, OnInit } from '@angular/core';
import { ChartDataset, ChartOptions, ChartType } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';
import { CursosService } from '../../../services/cursos.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-contabilizarsesiones',
  standalone: true,
  imports: [BaseChartDirective, CommonModule],
  templateUrl: './contabilizarsesiones.component.html',
  styleUrl: './contabilizarsesiones.component.css',
})
export class ContabilizarsesionesComponent implements OnInit {
  barChartOptions: ChartOptions = {
    responsive: true,
  };
  barChartLabels: string[] = [];
  barChartType: ChartType = 'pie';
  barChartLegend = true;
  barChartData: ChartDataset[] = [];
  chartWidth: string = '35%';
  constructor(private cS: CursosService) {}
  ngOnInit(): void {
    this.cS.cantidadSesionesCurso().subscribe((data) => {
      this.barChartLabels = data.map((item) => item.titulo);
      this.barChartData = [
        {
          data: data.map((item) => item.quatitySesion),
          label: 'Cantidad de sesiones',
          backgroundColor: this.generateColors(data.length), // Genera colores dinámicamente
          borderColor: '#e94215',
          borderWidth: 1,
        },
      ];
      if (data.length > 10) {
        this.barChartType = 'bar';
        this.barChartLegend = false;
        this.chartWidth = '70%';
        this.barChartOptions.plugins!.legend!.display = false;  // Oculta la leyenda para 'bar'
      } else {
        this.barChartType = 'pie';
        this.barChartLegend = true;
        this.chartWidth = '35%';
        this.barChartOptions.plugins!.legend!.display = true;  // Muestra la leyenda para 'pie'
      }
    });
  }
  generateColors(length: number): string[] {
    const colors: string[] = [];
    for (let i = 0; i < length; i++) {
        const color = `#${Math.floor(Math.random() * 16777215).toString(16)}`;
        colors.push(color);
    }
    return colors;
}
}
