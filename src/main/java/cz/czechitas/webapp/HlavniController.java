package cz.czechitas.webapp;

import java.util.*;
import java.util.concurrent.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class HlavniController {

    private Long sequence = 0L;
    private List<Contact> contacts = new CopyOnWriteArrayList(Arrays.asList(
            new Contact(++sequence, "Kamil Ševeček", "+420 604 111 222",
                    "kamil.sevecek@czachitas.cz"),
            new Contact(++sequence, "Kamil Tkadleček", "+420 732 333 444",
                    "kamil.tkadlecek@czechitas.cz")
    ));

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView viewIndex() {
        return new ModelAndView("redirect:/contact-list");
    }


    @RequestMapping(value = "/contact-list", method = RequestMethod.GET)
    public ModelAndView viewListOfContacts() {
        ModelAndView modelAndView = new ModelAndView("contact-list");
        modelAndView.addObject("contacts", contacts);
        return modelAndView;
    }

    @RequestMapping(value = "/contact-list/{id}", method = RequestMethod.POST,
            params = "method=DELETE")
    public ModelAndView deleteContact(@PathVariable("id") Long id) {
        deleteContactById(id);
        return new ModelAndView("redirect:/contact-list");
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ModelAndView viewDetail(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        modelAndView.addObject("contact", getContactById(id));
        return modelAndView;
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.POST)
    public ModelAndView submitDetail(@PathVariable("id") Long id, DetailForm detailForm) {
        editContact(id, detailForm);
        return new ModelAndView("redirect:/contact-list");
    }

    @RequestMapping(value = "/new-contact", method = RequestMethod.GET)
    public ModelAndView viewNewContact() {
        ModelAndView modelAndView = new ModelAndView("detail");
        modelAndView.addObject("contact", new DetailForm());
        return modelAndView;
    }

    @RequestMapping(value = "/new-contact", method = RequestMethod.POST)
    public ModelAndView submitNewContact(DetailForm detailForm) {
        saveContact(detailForm);
        return new ModelAndView("redirect:/contact-list");
    }

    private void deleteContactById(Long id) {
        int index = getIndexOfContactById(id);
        contacts.remove(index);
    }

    private void saveContact(DetailForm detailForm) {
        String name = detailForm.getName();
        String phoneNumber = detailForm.getPhoneNumber();
        String email = detailForm.getEmail();

        Contact newContact = new Contact(++sequence, name, phoneNumber, email);
        contacts.add(newContact);
    }

    private void editContact(Long id, DetailForm detailForm) {
        for (Contact contact : contacts) {
            if (contact.getId().equals(id)) {
                contact.setName(detailForm.getName());
                contact.setPhoneNumber(detailForm.getPhoneNumber());
                contact.setEmail(detailForm.getEmail());
                break;
            }
        }
    }

    private Contact getContactById(Long id) {
        int index = getIndexOfContactById(id);
        return contacts.get(index);
    }

    private int getIndexOfContactById(Long id) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}
