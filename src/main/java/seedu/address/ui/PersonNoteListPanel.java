package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;


/**
 * Panel containing the list of persons
 * */
public class PersonNoteListPanel extends UiPart<Region> {
    private static final String FXML = "PersonNoteListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Person> noteListView;

    /**
     * Creates a {@code PersonNoteListPanel} with the given {@code ObservableList}.
     */
    public PersonNoteListPanel(ObservableList<Person> personList) {
        super(FXML);
        noteListView.setItems(personList);
        noteListView.setCellFactory(listView -> new PersonNoteListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays only the note field of a {@code Person}.
     */
    class PersonNoteListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonNoteCard(person, getIndex() + 1, true).getRoot());
            }
        }
    }
}

