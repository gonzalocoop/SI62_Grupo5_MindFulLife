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
          borderColor: '#000000',
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
  const predefinedColors: string[] = [
    "#0a3866", "#004D40", "#FFE57F", "#ffb291", 
    "#0f5194", "#009688", "#00838F", "#1375d6", 
    "#B2DFDB", "#0097A7", "#807D7D", "#1b8eff", "#795548", 
    "#B2EBF2",  "#61a8ef", "#A1887F"
  ];

  // Aseguramos que la longitud solicitada no supere la cantidad de colores disponibles
  if (length > predefinedColors.length) {
      throw new Error("La longitud solicitada excede la cantidad de colores disponibles.");
  }

  const colors: string[] = [];
  const availableColors = [...predefinedColors]; // Copia del array original

  for (let i = 0; i < length; i++) {
      const randomIndex = Math.floor(Math.random() * availableColors.length);
      const color = availableColors.splice(randomIndex, 1)[0]; // Remueve y selecciona un color
      colors.push(color);
  }

  return colors;
}

}
