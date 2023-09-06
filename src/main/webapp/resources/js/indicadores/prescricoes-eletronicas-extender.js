function chartExtender() { 
    var labels = [];
    var data = this.cfg.config.data.datasets;
    var eletronicas = data[0].data;
    var manuais = data[1].data;

    for (var i = 0; i < eletronicas.length; ++i) {
		labels[i] = "E:" + eletronicas[i].toString() + "\nM:" + manuais[i].toString() + "\n(" +
		Math.round(eletronicas[i] / (eletronicas[i] + manuais[i]) * 100) + "%)";
	}

    var options = {
        plugins: [ChartDataLabels],
        options: {
            maintainAspectRatio: false,
            plugins: {
                datalabels: {
                    formatter: (value, ctx) => {
                        if (ctx.datasetIndex == 1)
                            return labels[manuais.indexOf(value)];
                        else
                            return "";
                    },
                    anchor: 'end',
                    align: 'end'
                }
            }
        }
    };
        
    $.extend(true, this.cfg.config, options);
};
 