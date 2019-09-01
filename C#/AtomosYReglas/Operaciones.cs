using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AtomosYReglas
{
    class Operaciones
    {
        private string consecuencia;
        private string antecedentes;
        private int posImplica = 0;

        /// <summary>
        /// Separa la regla ingresada, devuelve la lista de reglas para ser almacenadas.
        /// </summary>
        /// <param name="regla"></param>
        /// <returns></returns>
        public List<string> Separar(string regla)
        {
            List<string> lista = new List<string>();
            SepararCons(regla);
            Antecedentes(regla);

            //antecedentesLista = antecedentes.Split('v');

            return lista;
        }
        private void SepararCons(string regla)
        {
            for(int i=0;i<regla.Length;i++)
            {
                if(regla[i]=='>')
                {
                    posImplica = i;
                }
            }
            consecuencia = regla.Substring(posImplica + 1);
        }
        private void Antecedentes(string regla)
        {
            antecedentes = regla.Substring(0, posImplica-1);
        }
        private void SepararAnt()
        {
            string[] antecedentesLista;
            //antecedentesLista = antecedentes.Substring(x);
        }
    }
}
