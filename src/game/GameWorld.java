package game;


import java.util.*;
import static util.RemoveEndComma.removeEndComma;

/**
 * This class represents the game world - all the areas in the game.
 * {@link #currentArea} stores the information about the current location of the player.
 *
 * @author Martin Růžek
 * @version 1.0, 2023-06-09
 */
public class GameWorld {
    private Area currentArea;
    private Character murderer;
    private Map<String, Character> suspects;
    private Map<String, Area> areas;
    private Inventory inventory;
    private Map<String, Item> investigatedItems;

    private final String PARKING_LOT = "parking_lot";
    private final String HALL = "hall";
    private final String LIVING_ROOM = "living_room";
    private final String STUDY = "study";
    private final String LIBRARY = "library";
    private final String OFFICE = "office";
    private final String LOUNGE = "lounge";
    private final String BALCONY = "balcony";
    private final String GARDEN = "garden";
    private final String BACKYARD_GATE = "backyard_gate";

    /**
     * Instantiates a new Game world.
     *
     * @param inventory players inventory
     */
    public GameWorld(Inventory inventory) {
        // Areas
        Area parkingLot = new Area(PARKING_LOT, "You are in a parking lot in front of the Thrombey mansion.");

        Area hall = new Area(HALL, "You are in the main hall in the Thrombey mansion.");
        Area livingRoom = new Area(LIVING_ROOM, "You are in the living room.");
        Area study = new Area(STUDY, "You are in Harlan's study.", true);
        Area library = new Area(LIBRARY, "You are in the library.");
        Area office = new Area(OFFICE, "You are in Harlan's office.");
        Area lounge = new Area(LOUNGE, "You are in the lounge.");

        Area balcony = new Area(BALCONY, "You are on a balcony.");
        Area garden = new Area(GARDEN, "You are in a garden.");
        Area backyardGate = new Area(BACKYARD_GATE, "You are at a backyard gate.");
        Area nowhere = new Area("nowhere", "");

        areas = new HashMap<>();

        areas.put(PARKING_LOT, parkingLot);
        areas.put(HALL, hall);
        areas.put(LIVING_ROOM, livingRoom);
        areas.put(STUDY, study);
        areas.put(LIBRARY, library);
        areas.put(OFFICE, office);
        areas.put(LOUNGE, lounge);
        areas.put(BALCONY, balcony);
        areas.put(GARDEN, garden);
        areas.put(BACKYARD_GATE, backyardGate);

        // Area exits

        parkingLot.addExit(hall);
        parkingLot.addExit(garden);
        parkingLot.addExit(balcony);

        hall.addExit(parkingLot);
        hall.addExit(livingRoom);
        hall.addExit(study);
        hall.addExit(library);
        hall.addExit(office);
        hall.addExit(lounge);
        hall.addExit(balcony);

        livingRoom.addExit(hall);
        study.addExit(hall);
        library.addExit(hall);
        office.addExit(hall);
        lounge.addExit(hall);

        garden.addExit(parkingLot);
        garden.addExit(balcony);
        garden.addExit(backyardGate);

        balcony.addExit(garden);
        balcony.addExit(hall);
        balcony.addExit(parkingLot);

        backyardGate.addExit(garden);

        this.currentArea = parkingLot;

        // Items

        office.addItem(new Item("notepad",
                "This is a notepad. It has a pen attached to it. I'll take it with me, it may be useful."
        + "\n(You can use the notepad to write down clues you find. To write down a clue, type 'note' and follow it up with your note." +
                        "To read your notes, type 'read_notes'.)", true, null, "", false));
        office.addItem(new Item("study_key", "This is a key to the study. I'll take it with me, it may be useful.", true, study));
        study.addItem(new ItemVials("vials", "This is a set of vials - there is one empty vile of Morphine and half a vile of Toradol. But it looks like one vial is missing, that is strange. I'll take them with me, they may be useful later.",
                "Marta said that she gives him 1 full vial of Toradol and half a vial of Morphine, but these show otherwise." +
                "There is half a vial of Toradol and an empty vial of Morphine. That is very strange and very sus.\n", this));
        study.addItem(new Item("go_board", "There is a GO board on the floor. Somebody must've flipped the table in anger.", false));
        study.addItem(new Item("picture", "This is a picture of Harlan and Ransom Drysdale. That's wierd, why would he picture of only him on the wall? Strange.", false));
        livingRoom.addItem(new Item("report", "This is a copy of a toxicology report on Harlan. It says that the levels of Morphine and Toradol " +
                "were correct: 1 vile of Toradol and 1/2 of Morphine. I'll take it with me, it may be useful."));
        garden.addItem(new Item("dogs", "There are two dogs playing with something. I'll take a closer look. Oh, It's a missing wooden piece of the " +
                "plant growing grid! Somebody must've climbed into the top window!", false));
        garden.addItem(new Item("footprints", "There are some footprints in the mud. They lead from the backyard gate to the side of the house!", false));
        backyardGate.addItem(new Item("gate", "This is a gate. It leads to the backyard of the house.", false));
        backyardGate.addItem(new Item("footprints", "There are some footprints in the mud. They lead from here to the side of the house! They look kind of big, a man must've made them.", false));
        library.addItem(new Item("knife_wall", "Wow, this is a piece of art! It's a wall of knives. I'll take a closer look. Beautiful! The knives are even detachable, so you can take them out!", false));

        this.investigatedItems = new HashMap<>();

        // Characters/suspects

        this.suspects = new HashMap<>();

        // Linda
        HashMap<Area, String> lindaChats = new HashMap<>();
        lindaChats.put(lounge,
                "About Linda Drysdale (bron Linda Thrombey): She is Harlan's eldest daughter. Owns and runs her own company. She and her Husband Richard have a Ron: Hugh 'Ransom' Drysdale\n" +
                "Blanc: Hi, can I ask you a few questions?\n" +
                "Linda: Sure, what do you want to know?\n" +
                "Blanc: How was the party the night your father died?\n" +
                "Linda: The party? Pre my dad's death? It was great.\n" +
                "Blanc: Did anyone besides the family show face?\n" +
                "Linda: Uh, there was Fran, Meg, and Joni. And the nurse, Marta, Harlan's caregiver, good girl, hard worker. Family's from Ecuador..\n" +
                "Blanc: Okay, Your son Ransom, was he there as well?\n" +
                "Linda: Yes but he left early.\n" +
                "Blanc: did all three of you show up at around the same time?\n" +
                "Linda: N...o, Richard came early to help the caterers set up.\n" +
                "Blanc: Okay and you and your husband Richard work for a real estate firm in Boston?\n" +
                "Linda: It's my company. I built my business from the ground up.\n" +
                "Blanc: Just like your dad. You two were very close?\n" +
                "Linda: We had our own secret way of communicating.\n" +
                        "Blanc: I must say I respect all that you've accomplished as a family. Your father... You... Joni with her own thing... And Walt running the publishing empire...\n" +
                        "Linda: Well, Walt really just runs the business side of things. Our father was the one who made the books... It's not the same.\n" +
                        "Blanc: But surely Walt runs the merchandising, adaptations, film and television rights...\n" +
                        "Linda: Are you baiting me, Detective? You know he doesn't, and you think I'm dumb enough to be baited into talking family business, into shit talking my brother\n" +
                        "Linda: UNBELIEVABLE!"
        );
        CharLinda linda = new CharLinda("Linda", false, lounge, lindaChats, "Hi, I'm Linda", this);
        suspects.put(linda.getName(), linda);

        // Richard
        HashMap<Area, String> richardChats = new HashMap<>();
        richardChats.put(lounge,
                "About Richard Drysdale: Husband of Linda. He and his wife Linda have a son: Hugh 'Ransom' Drysdale.\n" +
                        "Blanc: Hi, may I ask you a few questions?\n" +
                        "Richard: Who the fuck are you?!.\n" +
                        "Blanc: My name is Benoit Blanc. I'm a private investigator of a sort. I'm here at the request of a client.\n" +
                        "Richard: And who is that?\n" +
                        "Blanc: I can't say. Can we focus of the questions please?\n" +
                        "Richard: Okay.\n" +
                        "Blanc: You are working for your wife's company. Correct?\n" +
                        "Richard: Yes.\n" +
                        "Blanc: I must say, I am quite impressed of how everyone in this family runs their own business... Your wife, Joni, Walter...\n" +
                        "Richard: Walter doesn't run shit! There are no film or TV rights, Harlan's never allowed any adaptations of his books. Hates the idea.\n" +
                        "Blanc: No!\n" +
                        "Richard: Oh yeah! Drives Walt nuts, cause that's where the real money's at. When he gets a little Irish courage in him he'll get into it with Harlan.\n" +
                        "Blanc: Did he get 'into it' at the party?\n" +
                        "Richard: Oh my god. He wouldn't leave him alone, poor guy. Harlan had to give him the hook. I didn't hear what he said but he must have really handed him his lunch, Walt was like a wounded puppy the rest of the night.\n" +
                        "Blanc: That was at the party the night your father-in-law died? How was that?\n" +
                        "Richard: It was great. We had a lot of fun.\n" +
                        "Blanc: Who else, besides the family, was there?\n" +
                        "Richard: Marta, Harlan's caregiver. Good kid been a good friend to Harlan. Her family's from Paraguay. Linda really likes her work ethic.\n" +
                        "Blanc: When did you arrive?\n" +
                        "Richard: I arrived early to help the caterers set up.\n" +
                        "Blanc: Really? Cuz I asked the caterers and they said they heard a screaming match between someone and Harlan.\n" +
                        "Richard: I don't... Screaming match? No. But Joni? She was here too? Maybe ask her.\n" +
                        "Blanc: They were 2 male voices. Harlan shouted: 'You will tell her or I will!' Bells ringing?\n" +
                        "Richard: ...\n" +
                        "Richard: Yes. I know - yes, ha. So. Harlan decided to finally put his mom in a nursing home. Which Linda always opposed.\n" +
                        "And I was going to wait till we were back home in Boston to tell her, so there wouldn't be a whole scene, but Harlan wanted me to\n" +
                        "tell her then. That was it. Sorry. Forgot.\n");
        CharRichard richard = new CharRichard("Richard", false, lounge, richardChats, "Hi, I'm Richard", this);
        suspects.put(richard.getName(), richard);

        // Walter
        HashMap<Area, String> walterChats = new HashMap<>();
        walterChats.put(lounge,
                "About Walter Thrombey: Harlan Thrombey's youngest son. Runs the family publishing company.\n" +
                        "Blanc: Hi, can I ask you a few questions?\n" +
                        "Walter: Sure.\n " +
                        "Blanc: You run the family publishing company. Correct?\n" +
                        "Walter: Yeah. It's my - it's our, it's the family's publishing company, dad trusts me to run it. 30 languages, over 80 million copies sold. A real legacy.\n" +
                        "Blanc: You live in town, right? When did you arrive at the party?\n" +
                        "Walter: Uh. We got here around 8. My wife Donna, she's my rock. And my son Jacob, he's sixteen. Very politically active.\n" +
                        "Blanc: So I've heard. Some call him nazi, others alt-right internet troll dipshit.\n" +
                        "Walter: Kids today, with the internet, amazing. He's a good kid. He's just passionate.\n" +
                        "Blanc: Oh yes. So how was the night?\n" +
                        "Walter: I mean. We're all gutted but I'm happy we got that night with him. To be by his side, to think about our books and what we've accomplished with them, it's like I can still feel his hand on my shoulder.\n" +
                        " Passing the torch.\n" +
                        "Blanc: Richard said that you and your father had a fight that night and that you got 'into it'.\n" +
                        "Walter: What? Richard said what? Jesus. No, we didn't get 'into it.'\n" +
                        "Walter: We talked, we had a business discussion, about e-books, Jesus, it was nothing. You want to talk about an argument, hell Ransom had an argument with him.\n" +
                        "Blanc: Ransom, Richard and Linda's son?\n" +
                        "Walter: Look we love Ransom, he is a good kid, we love him.\n" +
                        "Blanc: But...\n" +
                        "Walter: But he's always been the black sheep of the family, and I'm not, I, I keep stuff like this in the family, but with Ransom, he's never had a job.\n" +
                        "But dad for some unknown reason has always supported him, they've got this love hate bond. They fight.\n" +
                        "But that night, god. They had a blow out.\n" +
                        "Blanc: About what?\n" +
                        "Walter: We couldn't make it out, but it was huge. And it was strange they went in another room to do it - they usually love stoking up drama in front of the whole family.\n"
        );
        CharWalter walter = new CharWalter("Walter", false, lounge, walterChats, "Hi, I'm Walter", this);
        suspects.put(walter.getName(), walter);

        // Joni
        HashMap<Area, String> joniChats = new HashMap<>();
        joniChats.put(lounge,
                "About Joni Thrombey: Harlan Thrombey's daughter-in-law, widow to his son Niel Thrombey. Mother of Meg Thrombey.\n" +
                        "Blanc: I am sorry. Who are you?\n" +
                        "Joni: I'm Joni, I'm Niel Thrombey's widow and Harlan's daughter-in-law. And who are you?\n" +
                        "Blanc: My name is Benoit Blanc. I'm a private investigator of a sort. I'm here at the request of a client.\n" +
                        "Joni: Oh my god, I read a tweet about a New Yorker article about you. You're famous!\n" +
                        "Blanc: I'm not famous. I just do what I do and I'm good at it But I'm not here to talk about me. Would you mind if I asked you a few questions?\n" +
                        "Joni: Ofcourse.\n" +
                        "Blanc: I'm sorry,how are you connected to Harlan again?\n" +
                        "Joni: Mm. I was married to his son Neil, We had one daughter, Meg, and then Neil passed on fifteen years ago.\n" +
                        "Blanc: And you remained close to the Thrombeys?\n" +
                        "Joni: Oh they're my family. I feel simultaneously freed by and supported by them, that balance of opposites is the nugget of Flam.\n" +
                        "Blanc: Sorry, the Nugget of?\n" +
                        "Joni: Flam. it's skin care but it promotes a total lifestyle. Self sufficiency with an acknowledgment of human need. That's Flam, but it's also Harlan. He got me and Meg through some tough times." +
                        "Joni: You can ask her about that.\n" +
                        "Blanc: I will. So you were here the night of the party?\n" +
                        "Joni: Yes.\n" +
                        "Blanc: When did you arrive?\n" +
                        "Joni: The house?\n" +
                        "Blanc: Early. Richard said you were there when he arrived.\n" +
                        "Joni: I was. At the house early.\n" +
                        "Blanc: To see Harlan?\n" +
                        "Joni: To see Harlan. Yes.\n" +
                        "Blanc: What were you seeing Harlan about?\n" +
                        "Joni: It was just a mix up with the payment for Meg's tuition.\n" +
                        "Blanc: I'm sorry to press, what kind of mix up?\n" +
                        "Joni: Just a money wiring issue. With the office at the school. So I had to ask Harlan to cut a check for this semester. No big deal.\n" +
                        "Blanc: Thank you for your answers.\n");
        CharJoni joni = new CharJoni("Joni", false, lounge, joniChats, "Hi, I'm Joni", this);
        suspects.put(joni.getName(), joni);

        // Meg
        HashMap<Area, String> megChats = new HashMap<>();
        megChats.put(lounge,
                "About Meg Thrombey: Niel and Joni Thrombey's daughter. Goes to collage.\n" +
                        "Blanc: Hello, are you Meg Thrombey?\n" +
                        "Meg: Yeah. And you are?\n" +
                        "Blanc: Benoit Blanc. I'm a private investigator. May I ask you a few questions?\n" +
                        "Meg: Sure.\n" +
                        "Blanc: Your mother mentioned that Harlan has been supporting you and your mother for some time.\n" +
                        "Meg: Granddad gives my mom a yearly allowance, and he's never missed wiring a tuition payment to my schools. He's a genuinely selfless man.\n" +
                        "Blanc: And you were here the night of the party?\n" +
                        "Meg: Yeah, I was, but I left early to see some friends at Smith.\n" +
                        "Blanc: Thank you for your answers.\n"
        );
        CharMeg meg = new CharMeg("Meg", false, lounge, megChats, "Hi, I'm Meg", this);
        suspects.put(meg.getName(), meg);


        // Ransom
        HashMap<Area, String> ransomChats = new HashMap<>();
        CharRansom ransom = new CharRansom("Ransom", true, nowhere, ransomChats, this, "Wanna take a ride in the Beamer with me?");
        suspects.put(ransom.getName(), ransom);

        // Marta
        HashMap<Area, String> martaChats = new HashMap<>();
        martaChats.put(balcony,
                "About Marta Cabrera: Harlan Thrombey's caregiver, she is a foreigner, her mother is an illegal migrant.\n" +
                        "Blanc: Hello, you must be Harlan's nurse, Marta...\n" +
                        "Marta: Cabrera... Marta Cabrera.\n" +
                        "Blanc: Miss Cabrera, I been doing a little poking, you're hired on a part time basis as a registered nurse, yes?\n" +
                        "Marta: Yeah, I don't work for a VNA. Harlan hired me directly.\n" +
                        "Blanc: You're paid a flat rate for how many a week?\n" +
                        "Marta: I started at 15, but slowly he... needed more help... He needed a friend.\n" +
                        "Blanc: Does having a kind heart make you a good nurse? I'm sorry, inappropriate question. \n" +
                        "Blanc: Yes. Marta we were just discussing possible motives in the family. I suspect Harlan has told you much unfiltered truth about each of them, and a little bird has told me, how shall I put this delicately?\n" +
                        "You have a regurgitive reaction to mistruthin'.\n" +
                        "Marta: Who told you that?\n" +
                        "Blanc: Is it true?\n" +
                        "Marta: Yes. It's something that I have had as a kid. It's a physically thing that I - I - Just the thought of lying, yeah, it makes me puke.\n" +
                        "Blanc: Really? Is Richard having an affair?\n" +
                        "Marta: Heh - Richard? - affair? Heh... No.\n" +
                        "Marta: *vomits*\n" +
                        "Blanc: Whoa! Dear girl I'm sorry. I assumed you were speaking figuratively.\n" +
                        "Blanc: Quite something. But I was obviously right, Richard is having an affair, his father in law found out and confronted him. 'You tell her. Or I will.'\n" +
                        "Blanc: Well, even if that is right, protecting his marriage is weak sauce as a motive. Well. And then there is... Joni.\n" +
                        "Blanc: Miss Cabrera, was Harlan planning on cutting off Joni's allowance?\n" +
                        "Marta: Oh my god. I... \n" +
                        "Blanc: Don't answer that if you're going to puke. Please.\n" +
                        "Blanc: Meg said Harlan pays the school directly, Joni says he sends the money to her. Both were true, she was pocketing the double payment, Harlan found out and cut her off without a cent. Yes?\n" +
                        "Marta: No... Ehm... Well yes...\n" +
                        "Blanc: An allowance as a motive... She has her business. That's more weak sauce...\n" +
                        "Blanc: But she lied. To me. All of them did: Richard, Join and... Walter.\n" +
                        "Blanc: But there was something else. Harlan had turned Walter down before regarding film rights, but that night something Harlan said shook him.\n" +
                        "We look at the pattern, Harlan was cleaning house. I wonder... Did he plan to fire Walter?\n" +
                        "Marta: Can I wait inside? I don't feel like I should be here. I'll wait in the living room if you to ask me later.\n" +
                        "Blanc: Of course.\n");
        martaChats.put(livingRoom,
                "Blanc: Ms. Cabrera, we've kept you waiting all afternoon because I wanted to ear from you last. I wanted to have the entire picture of the evening in my head.\n" +
                        "Your piece of it is at its very center. So please, take your time. You took Mr. Thrombey upstairs at 11:30. And left at midnight.\n" +
                        "Think very carefully. And with as much detail as possible, tell us what happened in that half hour.\n" +
                        "Marta: I took him upstairs. We played our nightly game of GO, at some point he knocked the board over and Joni came up to check on us. Then I gave him pain medication, he pulled his shoulder last week, and left him in his study. \n" +
                        "At midnight. Said bye to Walt, went home.\n" +
                        "Blanc: What medication did he get?\n" +
                        "Marta: Since his injury I've been giving him a 1 full vile of Toradol, a non narcotic analgesic. And to help him sleep, 1/2 of morphine. This comes in a kit together with emergency shot of Naloxone.\n" +
                        "Blanc: Thank you for you cooperation, Ms. Cabrera.\n"
        );
        CharMarta marta = new CharMarta("Marta", false, balcony, martaChats, "Do you need anything?",this );
        suspects.put(marta.getName(), marta);

        // Detective Lieutenant Elliot
        HashMap<Area, String> detectiveChats = new HashMap<>();
        detectiveChats.put(balcony,
                "About Detective Lieutenant Elliot: Lt. Elliot has been dealing with this case for the past week.\n" +
                        "Blanc: Detective Lieutenant Elliot, I presume?\n" +
                        "Elliot: Yes, sir. I'm Lieutenant Elliot. I'm the lead detective on the case.\n" +
                        "Blanc: Walk me through everyone's whereabouts at the time of death, Lieutenant.\n" +
                        "Elliot: We know, the party broke up at 11:30.\n" +
                        "Marta took Harlan upstairs to give him his meds, Richard and Linda and Joni went right to bed. Now we do have this: the stairs leading up to Harlan's bedroom and his attic office creak horribly.\n" +
                        "And Linda is a light sleeper. So we know every time someone took the stairs that night.\n" +
                        "The first was when Joni heard a kaTHUNK from somewhere above her in the house.\n She's concerned about Harlan, she went up to investigate. Waking Linda.\n" +
                        "Joni knocks on the door of Harlan's attic office. It opens, and Harlan answers. In the room behind him we see Marta, her back turned, preparing a hypo needle.\n" +
                        "Harlan was in his attic office with Marta. He explained that they had just knocked the GO board over - that game with the grid and stones, they play it every night, and he was fine, go to bed. So she does.\n" +
                        "Ten minutes later, Linda is woken a second time, by Marta leaving. Walt was smoking a cigar on the porch with his son. He saw her leave and drive off, and noted the time - midnight.\n" +
                        "Fifteen minutes later, Linda is woken for the third and final time. By someone coming down the stairs. Harlan. Who came down for midnight snacks, which Walt tried to discourage.\n" +
                        "Based on this, the medical examiner determined time of death to be between 12:15 and 2am. As Walt was finishing his cigar, about 12:30, Meg came home. She went straight to bed. Walt and Jacob turned in shortly after that.\n" +
                        "Sometime later that night, undetermined but possibly near 3am, Meg woke up because the dogs were barking outside. She used the bathroom and went back to bed.\n" +
                        "And that's it. Everyone's stories matched, every movement accounted for.\n" +
                        "Blanc: Interesting.\n" +
                        "Police trooper standing aside: So I guess we can rule out Ransom, he wasn't there. And Marta, Harlan was alive after she left. But Meg got home during the time of death window.\n" +
                        "Elliot: Except it was a suicide. Harlan hit both carotids, we saw from the blood blood splat patterns that they were uninterrupted. Meaning, It's almost impossible for anyone to have been around him at the time.\n" +
                        "He's the one that cut his own throat. I don't know why we keep going over this.\n" +
                        "Blanc: Physical evidence can tell a clear story with a forked tongue. We should talk to Marta again.\n"
        );
        CharDetective detective = new CharDetective("Detective", false, balcony, detectiveChats, "I'm detective Lieutenant Elliot", this);
        suspects.put(detective.getName(), detective);

        // Jacob
        HashMap<Area,String> jacobChats = new HashMap<>();
        jacobChats.put(library,
                "About Jacob Thrombey: Teenager, son of Walter and Donna Thrombey. Politically active.\n" +
                        "Walter: Jacob, tell mister Blanc what you heard the night of the party.\n" +
                        "*Ransom walks in*\n" +
                        "Walter: Hah, look who decided to show up!\n" +
                        "*Ransom smirks at everyone*\n" +
                        "Walter: Jacob, please continue.\n" +
                        "Jacob: I was in the bathroom and I heard grandad and Ransom fighting in the office, but I only understood the words 'my will'.\n" +
                        "Linda: Ransom, what does that mean?\n" +
                        "Walter: It means dad finally came to his senses and cut this worthless lazy brat out of the will.\n" +
                        "Richard: Son.\n" +
                        "Ransom: Father?\n" +
                        "Richard: Did Harlan tell you he was cutting you out of the will?\n" +
                        "Ransom: Yup.\n" +
                        "Richard: Then he's done what we weren't strong enough to do - this might finally make you grow up.\n" +
                        "Linda: I think it might be the best thing that could happen to you.\n" +
                        "Ransom: Thanks - my mother, folks.\n" +
                        "Joni: It won't be easy for you but it'll be good. Nothing good is ever easy.\n" +
                        "Ransom: As a matter of fact - Eat shit, hows that? In fact eat shit, eat shit - eat shit - Definitely eat shit. Eat shit.\n\n" +
                        "*everybody's screaming at eachother*\n\n" +
                        "Alan Stevens (Harlan's Attorney): EVERYONE! PLEASE, CALM DOWN! I am here on behalf of late Harlan Thrombey and I would like to proceed with the will reading. So please, stay quiet.\n" +
                        "Alan: Well. Thank you all for getting together like this." +
                        "I thought this gathering would be, uh, beneficial is that as I told Walt, Harlan altered his will one week ago.\n" +
                        "He sealed it and asked me not to submit it to the courts for probate until after his death. So in case there's any confusion about anything we're all together, we can talk.\n" +
                        "I can't imagine any of it will be that complicated.\n" +
                        "Um, he did write up a statement when he made the recent changes, he wanted it read first, so:\n" +
                        "'Some of you may be surprised by the choice I've made here. No pleasure was taken in the exclusion, and its purpose was not to sow greater discord in the family, quite the opposite.\n" +
                        "Please accept it with grace and without bitterness. But do accept it. It's for the best.'\n" +
                        "Alan: And now, the will. 'I, Harlan Thrombey, being of sound mind and body, do hereby bequeath all of my assets and possessions to Marta Cabrera.'\n" +
                        "Walter: No.\n" +
                        "Linda: No.\n" +
                        "Walter: No. What? That can't be - that can't be right.\n" +
                        "Richard: What the genuine shit?\n" +
                        "Alan: It's right.\n" +
                        "Linda: No no no no Alan this can't be legal, there are, we're his family.\n" +
                        "Walter: We're his family, Alan he obviously wasn't, something - I don't know what but something wasn't right here.\n" +
                        "Joni: Alan there's a mistake.\n" +
                        "Meg: Mom if it's what granddad wanted?\n" +
                        "Joni: No this is a mistake, this is ours.\n" +
                        "Linda: No, we need to talk and we need to fight this thing and we're not going anywhere. GET OUT! We're the Thrombeys goddammit! This is still our house!\n" +
                        "Alan: Sorry, there's, uh. 'Likewise the house at two Deerborn Drive and all belongings therein I leave to Marta Cabrera.'\n" +
                        "Linda: You little bitch. Did you know about this? What did you do to him to make this happen, were you two what were you boinking my father?\n" +
                        "Ransom: *laughs out loud* I think this could be the best thing to happen to all of you!\n" +
                        "*everybody is screaming at eachother again*\n" +
                        "Blanc: EVERYBODY SHUT UP AND SIT DOWN!\n" +
                        "Blanc: My investigation has come to an end. I will now reveal who the murderer of your father, grandfather, friend, Harlan Thromeby, is.\n" +
                        "Blanc: During my investigation, I have found several pieces of evidence and mainly listened to all of you.\n" +
                        "Blanc: My first observation is that every single member of this family is a selfish bastard. There. I said it.\n" +
                        "Blanc: And the second, most important finding, is who the murderer of the great Harlan Thrombey is. So let's do a little summary, shall we?\n\n" +
                        "Blanc: One interesting thing that came out of your testimonials is that many of you got a motive. I won't go over the details... I don't want to cause a disturbance.\n" +
                        "Blanc: Anyway, from what I found that, one thing is clear. Somebody tried to frame Marta Cabrera so she couldn't be eligible to inherit Harlan's wealth.\n" +
                        "*everyone is in shock*\n" +
                        "Blanc: But that didn't really work out, cuz Marta is an excellent nurse... and person. Somebody switched the liquids inside" +
                        "the vials Marta used the night Harlan died. They switched the Toradol and Morphine so Harlan would overdose on Morphine.\n" +
                        "And they took the vile with Naloxone so Marta couldn't give him an emergency shot.\n" +
                        "Blanc: But Marta, is an excellent nurse. She gave Harlan the correct dosage because the liquids have different thickness and she picked the right vile just by looking at the density.\n" +
                        "Marta: So that means I didn't kill him?\n" +
                        "Blanc: Yes, the actual cause of death is really suicide.\n" +
                        "Marta: But why would somebody do that?\n" +
                        "Blanc: Excellent question. But first, let me show you how they did it.\n" +
                        "Blanc: From the footprints and the wooden break I found in the garden, it's safe to say that the killer sneaked in" +
                        "through the back gate and climbed up to Harlan's study through the window. \n" +
                        "Blanc: Now, back to your question: 'Why?'\n" +
                        "Blanc: It's simple. Somebody must've known that you were the only inheritor of Harlan's wealth." +
                        "But, if you would be the one that killed him, the will wouldn't be valid.\n" +
                        "Blanc: So the one person who did this known that Harlan's will was altered and they weren't at the Mansion at the time.\n" +
                        "Blanc: And that leaves us with only one suspect: \n" +
                        "(you can tag the murderer by typing 'murderer' plus the name of your suspect)\n\n"
        );
        CharJacob jacob = new CharJacob("Jacob", false, library, jacobChats, "TRUMP FOR PRESIDENT!", this);
        suspects.put(jacob.getName(), jacob);

        // Inventory
        this.inventory = inventory;
    }

    /**
     * Returns current area of the player.
     *
     * @return the current area of the player
     */
    public Area getCurrentArea() {
        return currentArea;
    }

    /**
     * Sets current area.
     *
     * @param area the area
     */
    public void setCurrentArea(Area area) {
        this.currentArea = area;
    }

    /**
     * Method sets the main suspect of the murder.
     *
     * @param murdererName the murderer name
     */
    public void setMurderer(String murdererName) {
        this.murderer = suspects.get(murdererName);
    }

    /**
     * Returns murderer.
     *
     * @return the murderer
     */
    public Character getMurderer() {
        return this.murderer;
    }

    /**
     * Returns Map of characters/suspects.
     *
     * @return the suspects
     */
    public Map getSuspects() {
        return suspects;
    }

    /**
     * Returns characters/suspects in current area.
     *
     * @param area the area
     * @return the suspects in area
     */
    public Map getSuspectsInArea(Area area) {
        Map<String, Character> result = new HashMap<>();

        for (Character ch : this.suspects.values()) {
            if(ch.getCurrentArea().equals(area)) {
                result.put(ch.getName(), ch);
            }
        }

        return result;
    }

    /**
     * Returns people in area in a form of a string.
     *
     * @param area the area
     * @return the people in area string
     */
    public String getPeopleInAreaString(Area area) {
        Map<String, Character> characters = new HashMap<>(this.getSuspectsInArea(area));

        String result = "";
        for (Character ch : characters.values()) {
            result += ch.getName() + ", ";
        }

        return removeEndComma(result);
    }

    /**
     * Returns suspect by name.
     *
     * @param name the name of the wanted suspect
     * @return the suspect
     */
    public Character getSuspect(String name) {
        return suspects.get(name);
    }

    /**
     * Returns all areas.
     *
     * @return the all areas
     */
    public Map<String,Area> getAllAreas() {
        return areas;
    }

    /**
     * Does character exist boolean.
     *
     * @param charName name of the requested character
     * @return boolean
     */
    public boolean doesCharacterExist(String charName) {
        return suspects.containsKey(charName);
    }

    /**
     * Returns link to player's inventory
     *
     * @return the inventory
     */
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * Add investigated item to the list.
     *
     * @param item the item
     */
    public void addInvestigatedItem(Item item) {
        this.investigatedItems.put(item.getName(), item);
    }

    /**
     * Has been investigated boolean.
     * Returns true if the item has been investigated already.
     *
     * @param item the item
     * @return the boolean
     */
    public boolean hasBeenInvestigated(String item) {
        return this.investigatedItems.containsKey(item);
    }

    /**
     * Returns true if it's possible to tag the murderer.
     *
     * @return the boolean
     */
    public boolean isGuessUnlocked() {
        if(this.suspects.get("Jacob").getChatCounter() == 1) {
            return true;
        }

        return false;
    }

    public boolean isDialogFinished() {
        for (Character c : this.suspects.values()) {
            if (c.getChatCounter() != c.getChatsAmount() && !c.getName().equals("Jacob")) {
                return false;
            }
        }

        return true;
    }

    public boolean areAllItemsFound() {
        if(this.getInventory().hasItem("vials")
        && this.getInventory().hasItem("report")
        && this.hasBeenInvestigated("dogs")
        && this.hasBeenInvestigated("footprints")) {
            return true;
        }

        return false;
    }

    /**
     * Returns current game state.
     *
     * @return the game state
     */
    public GameState getGameState() {
        if (murderer == null) {
            return GameState.PLAYING;
        } else if (murderer.getName().equals("Ransom")) {
            return GameState.WON;
        } else if (!murderer.getName().equals("Ransom")) {
            return GameState.LOST;
        }

        return GameState.PLAYING;
    }
}
