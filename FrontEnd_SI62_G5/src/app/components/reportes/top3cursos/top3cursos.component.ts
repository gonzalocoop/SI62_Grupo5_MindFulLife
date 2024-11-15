import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChartDataset, ChartOptions, ChartType } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';
import { ComentariosService } from '../../../services/comentarios.service';

@Component({
  selector: 'app-top3cursos',
  standalone: true,
  imports: [BaseChartDirective, CommonModule],
  templateUrl: './top3cursos.component.html',
  styleUrls: ['./top3cursos.component.css']
})
export class Top3cursosComponent implements OnInit {
  barChartOptions: ChartOptions = {
    responsive: true,
  };
  barChartLabels: string[] = [];
  barChartType: ChartType = 'pie';
  barChartLegend = true;
  barChartData: ChartDataset[] = [];
  hayDatos: boolean = false; // Cambiarlo a false por defecto para indicar que no hay datos

  constructor(private cS: ComentariosService) {}

  ngOnInit(): void {
    this.cS.top3Cursos().subscribe((data) => {
      // Filtra los cursos que tengan comentarios
      const cursosConComentarios = data.filter(item => item.cantidad > 0);

      if (cursosConComentarios.length > 0) {
        this.hayDatos = true;
        this.barChartLabels = cursosConComentarios.map(item => item.titulo);
        this.barChartData = [
          {
            data: cursosConComentarios.map(item => item.cantidad),
            label: 'Cantidad de comentarios',
            backgroundColor: ['#ee3007', '#f08e79', '#e98215'],
            borderColor: '#e94215',
            borderWidth: 1
          }
        ];
      } else {
        this.hayDatos = false; // Si no hay cursos con comentarios, mostramos el mensaje de error
      }
    });
  }
}
