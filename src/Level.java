/**
 * Level.java
 * Enum class for the level of the machine
 */
public enum Level{
    //counting/calculating/information/singularity
    Counting{
        public String toString(){
            return "Counting";
        }
    },
    Calculating{
        public String toString(){
            return "Calculating";
        }
    },
    Information{
        public String toString(){
            return "Information";
        }
    },
    Singularity{
        public String toString(){
            return "Singularity";
        }
    }
}
