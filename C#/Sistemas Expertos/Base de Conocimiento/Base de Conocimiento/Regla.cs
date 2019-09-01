using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Base_de_Conocimiento
{
    public class Regla
    {
        public string Texto { get; set; }
        public Atomo Consecuente { get; set; }
        public List<Atomo> Antecedentes  { get; set; }

        public Regla()
        {
            Antecedentes = new List<Atomo>();
            Consecuente = new Atomo();
        }
    }
    

}
