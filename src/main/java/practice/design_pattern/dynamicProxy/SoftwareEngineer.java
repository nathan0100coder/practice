package practice.design_pattern.dynamicProxy;








public class SoftwareEngineer implements Person{
   public SoftwareEngineer(){}
   public SoftwareEngineer(String name){
      this.name=name;
   }
   private String name;
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }

   @Override
   public void goWorking(String name, String dst) {
       System.out.println("name = "+ name + " ， 去 " + dst + " 工作");
   }
}