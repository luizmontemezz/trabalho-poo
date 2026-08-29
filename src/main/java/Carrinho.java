

import java.util.ArrayList;

public class Carrinho {

    private ArrayList<Roupas> itens = new ArrayList<>();

    public Carrinho() {
    }

    public void adicionarItem(Roupas roupa) {
        itens.add(roupa);
        System.out.println("Item adicionado ao carrinho com sucesso!");
    }

    public boolean removerItem(String codigoBarras) {
        for (Roupas r : itens) {
            if (r.getCodigoBarras().equals(codigoBarras)) {
                itens.remove(r);
                System.out.println("Item removido do carrinho com sucesso!");
                return true;
            }
        }
        System.out.println("Nenhum item com esse código de barras foi encontrado no carrinho.");
        return false;
    }

    public void listarItens() {
        if (itens.isEmpty()) {
            System.out.println("O carrinho está vazio.");
            return;
        }
        for (Roupas r : itens) {
            System.out.println(r);
        }
        System.out.println("Total do carrinho: R$" + calcularTotal());
    }

    public double calcularTotal() {
        double total = 0;
        for (Roupas r : itens) {
            total += r.getPreco();
        }
        return total;
    }

    public boolean isVazio() {
        return itens.isEmpty();
    }

    public ArrayList<Roupas> getItens() {
        return itens;
    }
}
