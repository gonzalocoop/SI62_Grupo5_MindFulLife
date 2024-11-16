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
          borderColor: '#000000',
          borderWidth: 1,
        },
      ];
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
