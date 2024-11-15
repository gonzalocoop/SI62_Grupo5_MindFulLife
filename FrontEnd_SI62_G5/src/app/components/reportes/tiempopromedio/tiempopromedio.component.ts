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
  styleUrls: ['./tiempopromedio.component.css']
})
export class TiempopromedioComponent implements OnInit {
  barChartOptions: ChartOptions = {
    responsive: true,
  };
  barChartLabels: string[] = [];
  barChartType: ChartType = 'bar';
  barChartLegend = false;
  barChartData: ChartDataset[] = [];
  chartWidth: string = '45%';
  hayDatos: boolean = true; // Controla si hay datos disponibles

  constructor(private sS: SesionesService) {}

  ngOnInit(): void {
    this.sS.tiempoPromedio().subscribe((data) => {
      if (data && data.length > 0) {
        this.hayDatos = true;
        this.barChartLabels = data.map((item) => item.tituloSesion);
        this.barChartData = [
          {
            data: data.map((item) => item.duracionPromedio),
            label: 'Duración promedio',
            backgroundColor: this.generateColors(data.length), // Colores dinámicos
            borderColor: '#e94215',
            borderWidth: 1,
          },
        ];
      } else {
        this.hayDatos = false; // Si no hay datos, mostramos el mensaje de error
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
