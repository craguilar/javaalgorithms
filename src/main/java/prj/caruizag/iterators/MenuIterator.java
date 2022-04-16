package prj.caruizag.iterators;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.util.Strings;

enum ItemType {
  CATEGORY, DISH, OPTION;
}



public class MenuIterator {

  private static final List<String> MENU_1 = new ArrayList<>(
      Arrays.asList("4", "DISH", "Spaghetti", "10.95", "2", "3", "", "1", "CATEGORY", "Pasta", "4",
          "5", "", "2", "OPTION", "Meatballs", "1.00", "", "3", "OPTION", "Chicken", "2.00", "",
          "5", "DISH", "Lasagna", "12.00", "", "6", "DISH", "Caesar Salad", "9.75", "3", ""));

  public static void main(String[] args) {
    MenuStream menuStream = new MenuStream(MENU_1);
    while (menuStream.hasNext()) {
      System.out.println(menuStream.next());
    }
  }

  private static class MenuStream implements Iterator<String> {

    private final Iterator<String> it;


    public MenuStream(List<String> lines) {
      this.it = lines.iterator();
    }

    @Override
    public boolean hasNext() {
      return it.hasNext();
    }

    @Override
    public String next() {
      if (this.hasNext()) {
        return parseMenu().toString();
      }
      throw new RuntimeException("Not additional menu items!");
    }


    private MenuItem parseMenu() {
      // 1. We split the lines in chunks separated by "" and we group them in array
      List<String> current = new ArrayList<>();
      while (it.hasNext()) {
        String line = it.next();
        if (line.isEmpty()) {
          return MenuItem.of(current);
        } else {
          current.add(line);
        }
      }
      throw new RuntimeException("Error has ocurred when parsin MenuItem!");// Throw an exception
    }


  }

  private static class MenuItem {
    /*
     * 
     * Line 0: The ID of the item Line 1: The item type, either CATEGORY, DISH or OPTION Line 2: The
     * name of the item Line 3: The price of the item for DISH and OPTION. Not present for CATEGORY
     * items. Any other line: A list of item IDs that are linked to the current item. OPTIONs do not
     * have any linked items.
     */
    private String id;
    private ItemType itemType;
    private String name;
    private BigDecimal price;
    private List<String> itemsId;

    public MenuItem(String id, ItemType itemType, String name, BigDecimal price,
        List<String> itemdsId) {
      this.id = id;
      this.itemType = itemType;
      this.name = name;
      this.price = price;
      this.itemsId = itemdsId;
    }

    public static MenuItem of(List<String> lines) {
      // Assert validations
      assert (!lines.isEmpty());
      assert (lines.size() >= 3);

      String id = lines.get(0);
      ItemType itemType = ItemType.valueOf(lines.get(1));
      String name = lines.get(2);
      BigDecimal price = null;
      // The price of the item for DISH and OPTION. Not present for CATEGORY items.
      int itemsStart = 3;
      if (itemType.equals(ItemType.DISH) || itemType.equals(ItemType.DISH)) {
        assert (lines.size() >= 4); // Throw an exception
        price = new BigDecimal(lines.get(3));
        itemsStart = 4;
      }
      List<String> itemsId = new ArrayList<>();
      while (itemsStart < lines.size()) {
        itemsId.add(lines.get(itemsStart));
        itemsStart++;
      }
      return new MenuItem(id, itemType, name, price, itemsId);

    }

    public String toString() {
      // This will be more efficient if it is a StringBuffer
      String priceStr = this.price != null ? this.price.toString() + "\n" : "";
      String value = this.id + "\n" + itemType + "\n" + this.name + "\n" + priceStr;
      String itemStr = Strings.join(itemsId, '\n');
      return value + itemStr + "\n";
    }
  }
}


