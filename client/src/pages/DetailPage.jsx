import Sidebar from "../components/Sidebar";
import ProgramDetail from "../components/ProgramDetail/ProgramDetail";

function DetailPage() {
  return(
    <div>
      <Sidebar
        items={[
          { title: "복지 검색 🔍" },
          { title: "복지 검색" },
          { title: "음성 검색" },
        ]}
      />
      <ProgramDetail></ProgramDetail>
    </div>
  )
}

export default DetailPage;