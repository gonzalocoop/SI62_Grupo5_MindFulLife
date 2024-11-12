import { Component, OnInit, ViewChild } from '@angular/core';
import { ChartConfiguration, ChartType } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';
import { CursosUsuariosService } from '../../../services/cursos-usuarios.service';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-cursoscompletadosynocompletados',
  standalone: true,
  imports: [BaseChartDirective],
  templateUrl: './cursoscompletadosynocompletados.component.html',
  styleUrl: './cursoscompletadosynocompletados.component.css'
})
export class CursoscompletadosynocompletadosComponent implements OnInit {
  @ViewChild(BaseChartDirective) chart?: BaseChartDirective;

  public barChartData: ChartConfiguration['data'] = {
    datasets: [
      {
        data: [],
        label: 'Cursos Completados',
        backgroundColor: '#4CAF50',
        borderColor: '#45a049',
        borderWidth: 1
      },
      {
        data: [],
        label: 'Cursos No Completados',
        backgroundColor: '#f44336',
        borderColor: '#da190b',
        borderWidth: 1
      }
    ],
    labels: []
  };

  public barChartOptions: ChartConfiguration['options'] = {
    responsive: true,
    plugins: {
      legend: {
        display: true,
        position: 'top'
      }
    }
  };

  public barChartType: ChartType = 'bar';

  constructor(
    private cuS: CursosUsuariosService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadChartData();
  }

  private loadChartData(): void {
    this.cuS.cantidadCursosCompletadosYNoCompletados().subscribe((data) => {
      this.barChartData.labels = data.map(item => item.username);
      this.barChartData.datasets[0].data = data.map(item => item.cursosCompletados);
      this.barChartData.datasets[1].data = data.map(item => item.cursosNoCompletados);
      
      // Forzar la actualización del gráfico
      this.chart?.update();
      this.cdr.detectChanges();
    });
  }
}