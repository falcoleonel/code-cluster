
a->b
c->b
a^d->c
a->f
d^b->g
h->d

a->b
b^d->c
c^e^f->g
a^e->h
h->~b

a->b
e->b
a^d->c
a->f
d^b->g
h->d
b^d->c
c^e^f->g
a^e->h
h->~b
c->i

You can also achieve this by using the List<T>'s contructor to specify a List<T> from which to copy from. eg var shallowClonedList = new List<MyObject>(originalList); – Arkiliknam Feb 16 '12 at 14:58
3
I often use List<int> newList = oldList.ToList(). Same effect. However, Arkiliknam's solution is best for readability in my opinion. – Dan Bechard Oct 25 '12 at 18:27


 public void MoveUp()
 {
     MoveItem(-1);
 }

 public void MoveDown()
 {
    MoveItem(1);
 }

 public void MoveItem(int direction)
 {
    // Checking selected item
    if (listBox1.SelectedItem == null || listBox1.SelectedIndex < 0)
        return; // No selected item - nothing to do

    // Calculate new index using move direction
    int newIndex = listBox1.SelectedIndex + direction;

    // Checking bounds of the range
    if (newIndex < 0 || newIndex >= listBox1.Items.Count)
        return; // Index out of range - nothing to do

    object selected = listBox1.SelectedItem;

    // Removing removable element
    listBox1.Items.Remove(selected);
    // Insert it in new position
    listBox1.Items.Insert(newIndex, selected);
    // Restore selection
    listBox1.SetSelected(newIndex, true);
}

public void MoveToTop(ListBox lb, int index) {
    var item = lb.Items[index];
    lb.Items.RemoveAt(index);
    lb.Items.Insert(0, item);
    lb.Refresh();
}

public void MoveToBottom(ListBox lb, int index) {
    var item = lb.Items[index];
    lb.Items.RemoveAt(index);
    lb.Items.Add(item);
    lb.Refresh();

//references

https://stackoverflow.com/questions/8391885/storing-data-into-list-with-class
https://dzone.com/articles/different-ways-of-creating-list-of-objects-in-c
https://stackoverflow.com/questions/4796109/how-to-move-item-in-listbox-up-and-down
public List<Regla> EncadenarAdelante(List<Regla> reglas)
        {
            //variables logica de paro y movimiento del proceso
            
            int posicion=0;
            {
                
                    for (int ant = 0; ant < reglas[posicion].Antecedentes.Count; ant++)
                    {
                        string Pregunta = $"�'{reglas[posicion].Antecedentes[ant]}' es verdadero?";
                        MessageBoxResult result = MessageBox.Show(Pregunta,
     "Pregunta", MessageBoxButton.YesNoCancel);
                        if (result == MessageBoxResult.Cancel)
                        {
                            break;
                        }
                        if (result == MessageBoxResult.No)
                        {
                            no = true;
                        }
                        else
                        {
                            si = true;
                        }
                }
            }

            return null;
        }
