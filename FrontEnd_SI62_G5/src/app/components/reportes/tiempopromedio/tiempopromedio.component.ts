import { Component, OnInit } from '@angular/core';
import { ChartDataset, ChartOptions, ChartType } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';
import { SesionesService } from '../../../services/sesiones.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-tiempopromedio',
  standalone: true,
  imports: [BaseChartDirective, CommonModule],
  templateUrl: './tiempopromedio.component.html',
  styleUrl: './tiempopromedio.component.css'
})
export class TiempopromedioComponent implements OnInit{
  barChartOptions: ChartOptions = {
    responsive: true,
  };
  barChartLabels: string[] = [];
  barChartType: ChartType = 'bar';
  barChartLegend = false;
  barChartData: ChartDataset[] = [];
  chartWidth: string = '45%';
  constructor(private sS: SesionesService) {}
  ngOnInit(): void {
    this.sS.tiempoPromedio().subscribe((data) => {
      this.barChartLabels = data.map((item) => item.tituloSesion);
      this.barChartData = [
        {
          data: data.map((item) => item.duracionPromedio),
          label: 'Duracion promedio',
          backgroundColor: this.generateColors(data.length), // Genera colores dinámicamente
          borderColor: '#e94215',
          borderWidth: 1,
        },
      ];
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
