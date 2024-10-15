import React from "react";
import { Button } from "@/components/ui/button";
import { useNavigate } from "react-router-dom";

function Home() {
  const navigate = useNavigate();

  return (
    <>
      <Button onClick={()=>navigate("/crud")}>Crud</Button>
      <Button onClick={()=>navigate("/overall")}>Overall</Button>
    </>
  );
}

export default Home;
