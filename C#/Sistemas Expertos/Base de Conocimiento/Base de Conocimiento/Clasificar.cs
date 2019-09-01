using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows;

namespace Base_de_Conocimiento
{
    static class Clasificar
    {
        #region propiedades

        //static List<Atomo> listaAtomos = new List<Atomo>();
        static List<Regla> listaReglas = new List<Regla>();
        //static List<string> atomos = new List<string>();
        const string rutaAtomos = "..\\..\\ArchivosDeTexto\\atomos.txt";
        const string rutaReglas = "..\\..\\ArchivosDeTexto\\reglas.txt";
        #endregion propiedades

        #region metodos

        //public static List<Atomo> CargarListaAtomos()
        //{
        //    AtomosALista(atomos);
        //    foreach (string a in atomos)
        //    {
        //        Atomo nuevo = new Atomo
        //        {
        //            Valor = a,
        //            Tipo = 'v'
        //        };
        //        listaAtomos.Add(nuevo);
        //    }
        //    return listaAtomos;
        //}

        //public static void AtomosALista(List<string> atomos)
        //{
        //    if (atomos.Count > 0)
        //        atomos.Clear();
        //    StreamReader archivo = new StreamReader(rutaAtomos);
        //    string atomo = archivo.ReadLine();
        //    while (atomo != null)
        //    {
        //        atomos.Add(atomo);
        //        atomo = archivo.ReadLine();
        //    }
        //    archivo.Close();
        //}

        /// <summary>
        /// Prove una lista de reglas señalando antecedentes y consecuentes y una copia de la regla original
        /// </summary>
        /// <returns>List<Regla></returns>
        public static List<Regla> ListadoReglas()
        {
            List<Regla> reglas = new List<Regla>();
            if (reglas.Count > 0)
                reglas.Clear();
            StreamReader archivo = new StreamReader(rutaReglas);
            string reglaTxt = archivo.ReadLine();
            while (reglaTxt != null)
            {
                Regla regla = new Regla();
                //Logica para capturar consecuente
                string tempCons = reglaTxt.Substring(reglaTxt.IndexOf('>') + 1);
                if (tempCons.Contains("~"))
                {
                    regla.Consecuente.Valor = tempCons.Substring(tempCons.IndexOf('~') + 1);
                    regla.Consecuente.Signo = 'f';
                }
                else
                {
                    regla.Consecuente.Valor = tempCons;
                    regla.Consecuente.Signo = 'v';
                }
                //Guardar regla en forma original para simplificar consulta
                regla.Texto = reglaTxt;
                //Logica para capturar antecedentes
                List<string> aRegla = Regex.Split(reglaTxt, @"[->^]").ToList();
                aRegla.RemoveAll(p => string.IsNullOrEmpty(p));
                aRegla.RemoveAll(p => string.IsNullOrWhiteSpace(p));
                aRegla.Remove(aRegla.Last());
                aRegla = aRegla.Select(t => t.Trim()).ToList();
                foreach (string atomo in aRegla)
                {
                    if (atomo != null)
                    {
                        Atomo nuevo = new Atomo();
                        if (atomo.Contains("~"))
                        {
                            nuevo.Valor = atomo.Substring(atomo.IndexOf('~') + 1); ;
                            nuevo.Signo = 'f';
                            regla.Antecedentes.Add(nuevo);
                        }
                        else
                        {
                            nuevo.Valor = atomo;
                            nuevo.Signo = 'v';
                            regla.Antecedentes.Add(nuevo);
                        }
                    }
                }
                reglas.Add(regla);
                reglaTxt = archivo.ReadLine();
            }
            archivo.Close();
            return reglas;
        }

        public static List<Atomo> ListadoConsecuentes(List<Regla> reglas)
        {
            List<Atomo> consecuentes = new List<Atomo>();
            foreach (Regla regla in reglas)
                consecuentes.Add(regla.Consecuente);
            return consecuentes;
        }
        public static List<Regla> OrganizarReglas(List<Regla> reglasDesordenadas)

        {
            List<Regla> reglasOrdenadas = new List<Regla>();

            for (int r = 0; r < reglasDesordenadas.Count; r++)
            {
                //Ciclo de Antecedentes
                bool movida = false;
                int a = 0;
                do
                {
                    //Ciclo de reglas a partir de la posicion de la regla actual
                    for (int pos = reglasDesordenadas.IndexOf(reglasDesordenadas[r]); pos < reglasDesordenadas.Count; pos++)
                    {
                        if (movida)
                            break;
                        if (reglasDesordenadas[r].Antecedentes[a].Valor == reglasDesordenadas[pos].Consecuente.Valor)
                        {
                            Regla temp = reglasDesordenadas[r];
                            reglasDesordenadas.Remove(reglasDesordenadas[r]);
                            reglasDesordenadas.Add(temp);
                            movida = true;
                            r -= 1;
                        }
                    }
                    a++;
                } while (a < reglasDesordenadas[r].Antecedentes.Count & !movida);

            }
            reglasOrdenadas = reglasDesordenadas;
            return reglasOrdenadas;
        }


        #endregion metodo
    }
}
